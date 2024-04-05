package ecp.ems.edge.basiccontroller;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.common.exceptions.OpenemsException;
import io.openems.common.types.ChannelAddress;
import io.openems.edge.common.channel.IntegerWriteChannel;
import io.openems.edge.common.component.AbstractOpenemsComponent;
import io.openems.edge.common.component.ComponentManager;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.controller.api.Controller;

@Designate(ocd = Config.class, factory = true)
@Component(//
		name = "Controller.ecp.ems.edge.basiccontroller", //
		immediate = true, //
		configurationPolicy = ConfigurationPolicy.REQUIRE //
)
public class BasicControllerImpl extends AbstractOpenemsComponent implements BasicController, Controller, OpenemsComponent {

	private final Logger log = LoggerFactory.getLogger(BasicControllerImpl.class);
	
	private Config config = null;
	
	@Reference
	private ComponentManager componentManager;

	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.OPTIONAL)
	
	private volatile ChannelAddress loadLevelChannelAddress;
	private volatile ChannelAddress vppModeChannelAddress;
	private Mode mode;
	
	

	public BasicControllerImpl() {
		super(//
				OpenemsComponent.ChannelId.values(), //
				Controller.ChannelId.values(), //
				BasicController.ChannelId.values() //
		);
	}

	@Activate
	private void activate(ComponentContext context, Config config) throws OpenemsNamedException {
		this.vppModeChannelAddress = ChannelAddress.fromString(config.vppModeChannelAddress());
		this.loadLevelChannelAddress = ChannelAddress.fromString(config.loadLevelChannelAddress());
		this.mode = config.mode();
		this.channel(BasicController.ChannelId.MODE).setNextValue(this.mode);
		super.activate(context, config.id(), config.alias(), config.enabled());
		//this.config = config;
	}

	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	public void run() throws OpenemsNamedException {
		boolean modeChanged;
		do {
			modeChanged = false;
			switch (this.mode) {
			case MANUAL_ON:
				this.manualOnOff(true);
				modeChanged = this.changeMode(Mode.MANUAL_ON);
				break;
			case MANUAL_OFF:
				this.manualOnOff(false);
				modeChanged = this.changeMode(Mode.MANUAL_OFF);
				break;
			case AUTOMATIC:
				this.automaticMode();
				modeChanged = this.changeMode(Mode.AUTOMATIC);
				break;
			}
		} while (modeChanged);
		
		this.channel(BasicController.ChannelId.MODE).setNextValue(this.mode);
	}
	
	/**
	 * Helper function for setting autormatic mode
	 * 
	 * @throws IllegalArgumentException
	 * @throws OpenemsNamedException
	 */
	private void automaticMode() throws IllegalArgumentException, OpenemsNamedException {
		
		this.setOperation(1, 100);
	};
	
	private void manualOnOff(Boolean value) throws IllegalArgumentException, OpenemsNamedException {
		
		if (value) { // True case - manual run, VPP mode 1, load level 100
			
			this.setOperation(1, 100);
		}
		else { // False case - stop, VPP mode 3, load level 0
			this.setOperation(3, 0);
		};
		
	};
	
	/**
	 * Helper function to write outputs to output channels
	 * 
	 * @param vppMode
	 * @param loadLevel
	 * @throws IllegalArgumentException
	 * @throws OpenemsNamedException
	 */
	private void setOperation(int vppMode, int loadLevel) throws IllegalArgumentException, OpenemsNamedException {
		
		try {
				IntegerWriteChannel vppChannel = this.componentManager.getChannel(vppModeChannelAddress);
				var currentVppValueOpt = vppChannel.value().asOptional();
				if (!currentVppValueOpt.isPresent() || currentVppValueOpt.get() != vppMode) {
					this.logInfo(this.log,  "Set output[" + vppChannel.address() + "]" + vppMode + ".");
					vppChannel.setNextWriteValue(vppMode);
				}
				
		} catch (OpenemsException e) {
			this.logError(this.log, "Unable to set output: [" + this.vppModeChannelAddress + "] " + e.getMessage());
		}
		
		try {
				IntegerWriteChannel loadLevelChannel = this.componentManager.getChannel(loadLevelChannelAddress);
				var currentLoadLevelOpt = loadLevelChannel.value().asOptional();
				if (!currentLoadLevelOpt.isPresent() || currentLoadLevelOpt.get() != loadLevel) {
					this.logInfo(this.log,  "Set outpu[" + loadLevelChannel.address() + "]" + loadLevel + ".");
					loadLevelChannel.setNextWriteValue(loadLevel);
				}
			
		} catch (OpenemsException e){
			this.logError(this.log, "Unable to set output: [" + this.loadLevelChannelAddress + "] " + e.getMessage());
			
		}
	}
	
	/**
	 * A flag to maintain change in the mode.
	 *
	 * @param nextMode the target mode
	 * @return Flag that the mode is changed or not
	 */
	private boolean changeMode(Mode nextMode) {
		if (this.mode != nextMode) {
			this.mode = nextMode;
			return true;
		}
		return false;
	}
	
}
