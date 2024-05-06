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

import io.openems.common.exceptions.OpenemsException;
import io.openems.edge.bridge.modbus.api.AbstractOpenemsModbusComponent;
import io.openems.edge.bridge.modbus.api.BridgeModbus;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.bridge.modbus.api.ModbusProtocol;
import io.openems.edge.bridge.modbus.api.element.CoilElement;
//import io.openems.edge.bridge.modbus.api.element.SignedWordElement;
import io.openems.edge.bridge.modbus.api.element.SignedWordElementFloat;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElement;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElementFloat;
import io.openems.edge.bridge.modbus.api.task.FC16WriteRegistersTask;
import io.openems.edge.bridge.modbus.api.task.FC1ReadCoilsTask;
import io.openems.edge.bridge.modbus.api.task.FC4ReadInputRegistersTask;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.taskmanager.Priority;

@Designate(ocd = Config.class, factory = true)
@Component(//
		name = "ecp.ems.edge.modbusgateway", //
		immediate = true, //
		configurationPolicy = ConfigurationPolicy.REQUIRE //
)
public class ModbusGatewayImpl extends AbstractOpenemsModbusComponent
		implements OpenemsComponent, ModbusComponent, ModbusGateway {

	@Reference
	private ConfigurationAdmin cm;


	@Override
	@Reference(policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.MANDATORY)
	protected void setModbus(BridgeModbus modbus) {
		super.setModbus(modbus);
	}

	private Config config;// = null;
	
	private long TimeOld;

	public ModbusGatewayImpl() {
		super(//
				OpenemsComponent.ChannelId.values(), //
				ModbusComponent.ChannelId.values(), //
				ModbusGateway.ChannelId.values() //
		);
	}

	@Activate
	private void activate(ComponentContext context, Config config) throws OpenemsException {
		this.config = config;
		
		this.TimeOld = System.currentTimeMillis() + config.modbusSlowReadDelay() * 1000;
		
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
		
		return new ModbusProtocol(this, //
				
				new FC16WriteRegistersTask(0, //
						m(ModbusGateway.ChannelId.VPP_MODE, new UnsignedWordElement(0)),//
						m(ModbusGateway.ChannelId.CHP_LOAD_LEVEL, new UnsignedWordElement(1))//
				),

				new FC1ReadCoilsTask(0, Priority.HIGH,//
						m(ModbusGateway.ChannelId.ERROR_ACTIVE, new CoilElement(0)),//
						m(ModbusGateway.ChannelId.RUNNING_ACTIVE, new CoilElement(1)),//
						m(ModbusGateway.ChannelId.READY_ACTIVE, new CoilElement(2)),//
						m(ModbusGateway.ChannelId.NOT_READY_ACTIVE, new CoilElement(3))//
				),

				new FC1ReadCoilsTask(4, Priority.LOW,//
						m(ModbusGateway.ChannelId.ODD_STORAGE, new CoilElement(4)),//
						m(ModbusGateway.ChannelId.STORAGE_SEQUENCE_FOUND, new CoilElement(5))//
				),



				new FC4ReadInputRegistersTask(0, Priority.LOW,//
						m(ModbusGateway.ChannelId.STORAGE_TOP_TEMPERATURE, new SignedWordElementFloat(0), SCALE_FACTOR_MINUS_2),// 0x0000
						m(ModbusGateway.ChannelId.STORAGE_BOTTOM_TEMPERATURE, new SignedWordElementFloat(1), SCALE_FACTOR_MINUS_2),// 0x0001
						m(ModbusGateway.ChannelId.FLOW_FORWARD_TEMPERATURE, new SignedWordElementFloat(2), SCALE_FACTOR_MINUS_2),// 0x0002
						m(ModbusGateway.ChannelId.FLOW_RETURN_TEMPERATURE, new SignedWordElementFloat(3), SCALE_FACTOR_MINUS_2),// 0x0003
						m(ModbusGateway.ChannelId.CHP_TO_NET_TEMPERATURE, new SignedWordElementFloat(4), SCALE_FACTOR_MINUS_2)// 0x0004
						),

				new FC4ReadInputRegistersTask(6, Priority.LOW,//
						m(ModbusGateway.ChannelId.POWER_ELECTRICAL, new UnsignedWordElementFloat(6), SCALE_FACTOR_MINUS_1),	// 0x0006
						m(ModbusGateway.ChannelId.COOLING_CAPACITY, new UnsignedWordElement(7))								// 0x0007
						),

				new FC4ReadInputRegistersTask(16, Priority.LOW,//
						m(ModbusGateway.ChannelId.SUM_RUNNING_HOURS, new UnsignedWordElement(16)),		// 0x0010
						m(ModbusGateway.ChannelId.HOURS_TO_SERVICE, new UnsignedWordElement(17)),		// 0x0011
						m(ModbusGateway.ChannelId.ERROR_CODE, new UnsignedWordElement(18)),				// 0x0012
						m(ModbusGateway.ChannelId.SUM_GENERATOR_STARTS, new UnsignedWordElement(19)), 	// 0x0013
						m(ModbusGateway.ChannelId.HEAT_CONTROL_TMV, new UnsignedWordElementFloat(20), SCALE_FACTOR_MINUS_2), 		// 0x0014
						m(ModbusGateway.ChannelId.HEAT_CONTROL_TMK, new UnsignedWordElementFloat(21), SCALE_FACTOR_MINUS_2), 		// 0x0015
						m(ModbusGateway.ChannelId.HEAT_CONTROL_TLV, new UnsignedWordElementFloat(22), SCALE_FACTOR_MINUS_2), 		// 0x0016
						m(ModbusGateway.ChannelId.HEAT_CONTROL_TLK, new UnsignedWordElementFloat(23), SCALE_FACTOR_MINUS_2), 		// 0x0017
						m(ModbusGateway.ChannelId.HEAT_CONTROL_TReturn, new UnsignedWordElementFloat(24), SCALE_FACTOR_MINUS_2), 	// 0x0018
						m(ModbusGateway.ChannelId.HEAT_CONTROL_SETPOINT, new UnsignedWordElementFloat(25), SCALE_FACTOR_MINUS_2),	// 0x0019
						m(ModbusGateway.ChannelId.FLOW_MASTER_BYPASS, new UnsignedWordElementFloat(26), SCALE_FACTOR_MINUS_2),		// 0x001A
						m(ModbusGateway.ChannelId.FLOW_MASTER_SOURCE, new UnsignedWordElementFloat(27), SCALE_FACTOR_MINUS_2),		// 0x001B
						m(ModbusGateway.ChannelId.FLOW_MASTER_SETPOINT, new UnsignedWordElementFloat(28), SCALE_FACTOR_MINUS_2),	// 0x001C
						m(ModbusGateway.ChannelId.FLOW_MASTER_OPERATIONAL_SETPOINT, new UnsignedWordElementFloat(29), SCALE_FACTOR_MINUS_2),	// 0x001D
						m(ModbusGateway.ChannelId.HEAT_CONTROL_ENGINE_PUMP_LEVEL, new UnsignedWordElement(30)), 	// 0x001E
						m(ModbusGateway.ChannelId.HEAT_CONTROL_STORAGE_PUMP_LEVEL, new UnsignedWordElement(31)), 	// 0x001F
						m(ModbusGateway.ChannelId.FLOW_MASTER_PUMP_LEVEL, new UnsignedWordElement(32)), 			// 0x0020
						m(ModbusGateway.ChannelId.HEAT_CONTROL_VALVE_POSITION, new UnsignedWordElement(33)), 		// 0x0021
						m(ModbusGateway.ChannelId.FLOW_MASTER_VALVE_POSITION, new UnsignedWordElement(34)), 				// 0x0022
						m(ModbusGateway.ChannelId.POWER_THERMAL, new UnsignedWordElementFloat(35), SCALE_FACTOR_MINUS_2),	// 0x0023
						m(ModbusGateway.ChannelId.HEAT_EXCHANGER_CONDITION, new UnsignedWordElementFloat(36), SCALE_FACTOR_MINUS_1), 	 //0x0024
						m(ModbusGateway.ChannelId.SEPARATION_LAYER_TEMPERATURE, new UnsignedWordElementFloat(37), SCALE_FACTOR_MINUS_2), // 0x0025
						m(ModbusGateway.ChannelId.PU_REQUESTED_POWER, new UnsignedWordElement(38)), // 0x0026
						m(ModbusGateway.ChannelId.PU_LGVGP_LIMIT, new UnsignedWordElement(39)), 	// 0x0027
						m(ModbusGateway.ChannelId.PU_AIM_POWER, new UnsignedWordElement(40)), 		// 0x0028
						m(ModbusGateway.ChannelId.PU_STEP_POSITION, new UnsignedWordElement(41)), 	// 0x0029
						m(ModbusGateway.ChannelId.PU_MAP_PRESSURE, new UnsignedWordElement(42)), 	// 0x002A
						m(ModbusGateway.ChannelId.PU_FUEL_STEP_POSITION, new UnsignedWordElement(43)), // 0x002B
						m(ModbusGateway.ChannelId.PU_IGNITION_ANGLE, new UnsignedWordElementFloat(44), SCALE_FACTOR_MINUS_1), 	//0x002C
						m(ModbusGateway.ChannelId.PU_WATER_TEMPERATURE, new UnsignedWordElementFloat(45), SCALE_FACTOR_MINUS_2),//0X002D
						m(ModbusGateway.ChannelId.PU_RPM, new UnsignedWordElement(46)), 			// 0x002E
						m(ModbusGateway.ChannelId.L1L2_VOLTAGE, new UnsignedWordElement(47)),// 0x002F
						m(ModbusGateway.ChannelId.L2L3_VOLTAGE, new UnsignedWordElement(48)),// 0x0030
						m(ModbusGateway.ChannelId.L3L1_VOLTAGE, new UnsignedWordElement(49)),// 0x0031
						m(ModbusGateway.ChannelId.GRID_FREQUENCY, new UnsignedWordElementFloat(50), SCALE_FACTOR_MINUS_2),// 0x0032
						m(ModbusGateway.ChannelId.SYSTEM_STATUS, new UnsignedWordElement(51))// 0x0033
				)
				);
		
	}

	/*
	@Override
	public ModbusSlaveTable getModbusSlaveTable(AccessMode accessMode) {
		return new ModbusSlaveTable(//
				OpenemsComponent.getModbusSlaveNatureTable(accessMode), //
				ModbusSlaveNatureTable.of(ModbusGateway.class, accessMode, 100) //
				.build()
		);
	}*/

//	@Override
//	public void setStartStop(StartStop value) throws OpenemsNamedException {
//		// TODO Auto-generated method stub
//	}

	
	@Override
	public String debugLog() {
		return "Modbus Gateway interface";
	}
}
