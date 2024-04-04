package ecp.ems.edge.modbusgateway;

import static io.openems.edge.bridge.modbus.api.ElementToChannelConverter.SCALE_FACTOR_MINUS_1;
import static io.openems.edge.bridge.modbus.api.ElementToChannelConverter.SCALE_FACTOR_MINUS_2;

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

import io.openems.common.channel.AccessMode;
import io.openems.common.exceptions.OpenemsError.OpenemsNamedException;
import io.openems.common.exceptions.OpenemsException;
import io.openems.edge.bridge.modbus.api.AbstractOpenemsModbusComponent;
import io.openems.edge.bridge.modbus.api.BridgeModbus;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.bridge.modbus.api.ModbusProtocol;
import io.openems.edge.bridge.modbus.api.element.CoilElement;
import io.openems.edge.bridge.modbus.api.element.SignedWordElement;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElement;
import io.openems.edge.bridge.modbus.api.task.FC16WriteRegistersTask;
import io.openems.edge.bridge.modbus.api.task.FC1ReadCoilsTask;
import io.openems.edge.bridge.modbus.api.task.FC4ReadInputRegistersTask;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.modbusslave.ModbusSlave;
import io.openems.edge.common.modbusslave.ModbusSlaveTable;
import io.openems.edge.common.startstop.StartStop;
import io.openems.edge.common.startstop.StartStoppable;
import io.openems.edge.common.taskmanager.Priority;

@Designate(ocd = Config.class, factory = true)
@Component(//
		name = "ecp.ems.edge.modbusgateway", //
		immediate = true, //
		configurationPolicy = ConfigurationPolicy.REQUIRE //
)
public class ModbusGatewayImpl extends AbstractOpenemsModbusComponent implements ModbusGateway, ModbusComponent, ModbusSlave, OpenemsComponent, StartStoppable {

	@Reference
	private ConfigurationAdmin cm;
	
	
	@Override
	@Reference(policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.MANDATORY)
	protected void setModbus(BridgeModbus modbus) {
		super.setModbus(modbus);
	}

	private Config config = null;

	public ModbusGatewayImpl() {
		super(//
				OpenemsComponent.ChannelId.values(), //
				ModbusComponent.ChannelId.values(), //
				ModbusGateway.ChannelId.values(), //
				StartStoppable.ChannelId.values() //
		);
	}

	@Activate
	private void activate(ComponentContext context, Config config) throws OpenemsException {
		this.config = config;
		if(super.activate(context, config.id(), config.alias(), config.enabled(), config.modbusUnitId(), this.cm, "Modbus",
				config.modbus_id())) {
			return;
		}
	}

	@Override
	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	protected ModbusProtocol defineModbusProtocol() throws OpenemsException {

		return new ModbusProtocol(this,//
				
				new FC16WriteRegistersTask(0, //
						m(ModbusGateway.ChannelId.VPP_MODE, new UnsignedWordElement(0)),//
						m(ModbusGateway.ChannelId.CHP_LOAD_LEVEL, new UnsignedWordElement(1))//
				),
				
				new FC1ReadCoilsTask(0, Priority.HIGH,//
						m(ModbusGateway.ChannelId.ERROR_ACTIVE, new CoilElement(0)),//
						m(ModbusGateway.ChannelId.RUNNING_ACTIVE, new CoilElement(1)),//
						m(ModbusGateway.ChannelId.READY_ACTIVE, new CoilElement(2))//
				),
				
				new FC1ReadCoilsTask(5, Priority.HIGH,//
						m(ModbusGateway.ChannelId.STORAGE_SEQUENCE_FOUND, new CoilElement(5))//
				),
				
		
				
				new FC4ReadInputRegistersTask(0, Priority.HIGH,//
						m(ModbusGateway.ChannelId.STORAGE_TOP_TEMPERATURE, new SignedWordElement(0), SCALE_FACTOR_MINUS_2),//
						m(ModbusGateway.ChannelId.STORAGE_BOTTOM_TEMPERATURE, new SignedWordElement(1), SCALE_FACTOR_MINUS_2),//
						m(ModbusGateway.ChannelId.FLOW_FORWARD_TEMPERATURE, new SignedWordElement(2), SCALE_FACTOR_MINUS_2),//
						m(ModbusGateway.ChannelId.FLOW_RETURN_TEMPERATURE, new SignedWordElement(3), SCALE_FACTOR_MINUS_2),//
						m(ModbusGateway.ChannelId.CHP_TO_NET_TEMPERATURE, new SignedWordElement(4), SCALE_FACTOR_MINUS_2)//
						),
				
				new FC4ReadInputRegistersTask(6, Priority.HIGH,//
						m(ModbusGateway.ChannelId.POWER_ELECTRICAL, new UnsignedWordElement(6), SCALE_FACTOR_MINUS_1),//
						m(ModbusGateway.ChannelId.COOLING_CAPACITY, new UnsignedWordElement(7))//
						),
				
				new FC4ReadInputRegistersTask(16, Priority.HIGH,//
						m(ModbusGateway.ChannelId.SUM_RUNNING_HOURS, new UnsignedWordElement(16)),//
						m(ModbusGateway.ChannelId.HOURS_TO_SERVICE, new UnsignedWordElement(17)),//
						m(ModbusGateway.ChannelId.ERROR_CODE, new UnsignedWordElement(18))//
						),
				
				new FC4ReadInputRegistersTask(35, Priority.HIGH,//
						m(ModbusGateway.ChannelId.POWER_THERMAL, new UnsignedWordElement(35))//
						),
				
				new FC4ReadInputRegistersTask(37, Priority.HIGH,//
						m(ModbusGateway.ChannelId.SEPARATION_LAYER_TEMPERATURE, new SignedWordElement(37), SCALE_FACTOR_MINUS_2)//
						),
				
				new FC4ReadInputRegistersTask(47, Priority.HIGH,//
						m(ModbusGateway.ChannelId.L1L2_VOLTAGE, new UnsignedWordElement(47)),//
						m(ModbusGateway.ChannelId.L2L3_VOLTAGE, new UnsignedWordElement(48)),//
						m(ModbusGateway.ChannelId.L3L1_VOLTAGE, new UnsignedWordElement(49)),//
						m(ModbusGateway.ChannelId.GRID_FREQUENCY, new UnsignedWordElement(50)),//
						m(ModbusGateway.ChannelId.SYSTEM_STATUS, new UnsignedWordElement(51))//
				)
				
				);
	}
	
	@Override
	public ModbusSlaveTable getModbusSlaveTable(AccessMode accessMode) {
		return new ModbusSlaveTable(//
				OpenemsComponent.getModbusSlaveNatureTable(accessMode) //
		);
	}

	@Override
	public void setStartStop(StartStop value) throws OpenemsNamedException {
		// TODO Auto-generated method stub
	}

	@Override
	public String debugLog() {
		return "Hello World";
	}
}
