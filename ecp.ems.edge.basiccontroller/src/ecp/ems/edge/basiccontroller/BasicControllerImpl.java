package ecp.ems.edge.basiccontroller;

import org.osgi.service.cm.ConfigurationAdmin;
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
	private ConfigurationAdmin cm;
	
	@Reference
	private ComponentManager componentManager;

	@Reference(policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.OPTIONAL)
	
	private volatile ChannelAddress loadLevelChannelAddress;
	private volatile ChannelAddress vppModeChannelAddress;
	private volatile ChannelAddress vppModeDebugChannelAddress;
	private Mode mode;


	private int VppModeRun = 1;
	private int VppModeStop = 3;
	private int LoadLevelRun = 100;
	private int LoadLevelStop = 0;

	
	
	private boolean firstRun = true;
	
	

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
		// Get debug channel for persistence read of last send value:
		this.vppModeDebugChannelAddress = ChannelAddress.fromString(this.vppModeChannelAddress.getComponentId() + "/VppModeDebug");
		this.loadLevelChannelAddress = ChannelAddress.fromString(config.loadLevelChannelAddress());
		this.mode = config.mode();
		this.channel(BasicController.ChannelId.MODE).setNextValue(this.mode);
		
//		//Reading in values from 
//		this.VppModeRun = <Integer>this.channel(BasicController.ChannelId.VPP_MODE_RUN).value().get();
		
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
				modeChanged = this.changeMode(Mode.MANUAL_ON);
				if (modeChanged) {
					this.manualOnOff(true);
				}
				break;
			case MANUAL_OFF:
				modeChanged = this.changeMode(Mode.MANUAL_OFF);
				if (modeChanged) {
					this.manualOnOff(false);
				}
				break;
			case AUTOMATIC:
				modeChanged = this.changeMode(Mode.AUTOMATIC);
				if (modeChanged) {
					this.automaticMode();
			}
				
				break;
			}
			this.channel(BasicController.ChannelId.MODE).setNextValue(this.mode);
		} while (modeChanged);
		

	}
	
	/**
	 * Helper function for setting automatic mode
	 * 
	 * Applies internal channels as VPP mode for running and load level.
	 * 
	 * @throws IllegalArgumentException
	 * @throws OpenemsNamedException
	 */
	private void automaticMode() throws IllegalArgumentException, OpenemsNamedException {
		
		this.setOperation(this.VppModeRun, this.LoadLevelRun);
		
	};
	
	/**
	 * Helper function for setting manual operation modes (on or off).
	 * 
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws OpenemsNamedException
	 */
	private void manualOnOff(Boolean value) throws IllegalArgumentException, OpenemsNamedException {
		
		if (value) { // True case - manual run, VPP mode 1, load level 100
			
			this.setOperation(this.VppModeRun, this.LoadLevelRun);
		}
		else { // False case - stop, VPP mode 3, load level 0
			this.setOperation(this.VppModeStop, this.LoadLevelStop);
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
				IntegerWriteChannel vppChannel = this.componentManager.getChannel(this.vppModeChannelAddress);
						this.logInfo(this.log,  "Set output[" + vppChannel.address() + "]" + vppMode + ".");
						vppChannel.setNextWriteValue(vppMode);
				
		} catch (OpenemsException e) {
			this.logError(this.log, "Unable to set output: [" + this.vppModeChannelAddress + "] " + e.getMessage());
		}
		
		try {
				IntegerWriteChannel loadLevelChannel = this.componentManager.getChannel(this.loadLevelChannelAddress);
						this.logInfo(this.log,  "Set output[" + loadLevelChannel.address() + "]" + loadLevel + ".");
						loadLevelChannel.setNextWriteValue(loadLevel);
			
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
		if (!this.mode.equals(nextMode) || this.firstRun) {
			this.mode = nextMode;
			this.firstRun = false;
			return true;
		} else {
		return false;
		}
	}
	
}
