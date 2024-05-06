package ecp.ems.edge.modbusgateway;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;

public interface ModbusGateway extends ModbusComponent, OpenemsComponent {
	

	public static enum ChannelId implements io.openems.edge.common.channel.ChannelId {

		// ==== Variables written FC16 ====
		VPP_MODE_DEBUG(Doc.of(OpenemsType.INTEGER)),// 0x0000
		VPP_MODE(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.READ_WRITE) //
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.VPP_MODE_DEBUG)), //

		CHP_LOAD_LEVEL_DEBUG(Doc.of(OpenemsType.INTEGER)),// 0x0001
		CHP_LOAD_LEVEL(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_WRITE)//
				.unit(Unit.PERCENT)//
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.CHP_LOAD_LEVEL_DEBUG)),

		// ===== COILS FC01 ==== 
		ERROR_ACTIVE(Doc.of(OpenemsType.BOOLEAN)// 0x0000
				.accessMode(AccessMode.READ_ONLY)),//

		RUNNING_ACTIVE(Doc.of(OpenemsType.BOOLEAN)// 0x0001
				.accessMode(AccessMode.READ_ONLY)),//

		READY_ACTIVE(Doc.of(OpenemsType.BOOLEAN)// 0x0002
				.accessMode(AccessMode.READ_ONLY)),//
		
		NOT_READY_ACTIVE(Doc.of(OpenemsType.BOOLEAN)// 0x0003
				.accessMode(AccessMode.READ_ONLY)),//
		
		ODD_STORAGE(Doc.of(OpenemsType.BOOLEAN)// 0x0004
				.accessMode(AccessMode.READ_ONLY)),//
		
		STORAGE_SEQUENCE_FOUND(Doc.of(OpenemsType.BOOLEAN)// 0x0005 
				.accessMode(AccessMode.READ_ONLY)),//
		
		
		// ==== Variable read FC04 ====
		STORAGE_TOP_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0000
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		STORAGE_BOTTOM_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0001
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		FLOW_FORWARD_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0002
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		FLOW_RETURN_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0003
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		CHP_TO_NET_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0004
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//
		
		POWER_ELECTRICAL(Doc.of(OpenemsType.FLOAT)// 0x0006
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.KILOWATT)),//
		
		COOLING_CAPACITY(Doc.of(OpenemsType.INTEGER)// 0x0007
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.PERCENT)),//

		SUM_RUNNING_HOURS(Doc.of(OpenemsType.INTEGER)// 0x0010
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HOUR)),//
		
		HOURS_TO_SERVICE(Doc.of(OpenemsType.INTEGER)// 0x0011
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HOUR)),//

		ERROR_CODE(Doc.of(OpenemsType.INTEGER)// 0x0012
				.accessMode(AccessMode.READ_ONLY)),//
		
		SUM_GENERATOR_STARTS(Doc.of(OpenemsType.INTEGER) // 0x0013
				.accessMode(AccessMode.READ_ONLY)),// 
				
		HEAT_CONTROL_TMV(Doc.of(OpenemsType.FLOAT)// 0x0014
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HEAT_CONTROL_TMK(Doc.of(OpenemsType.FLOAT)// 0x0015
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),

		HEAT_CONTROL_TLV(Doc.of(OpenemsType.FLOAT)// 0x0016
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HEAT_CONTROL_TLK(Doc.of(OpenemsType.FLOAT)// 0x0017
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HEAT_CONTROL_TReturn(Doc.of(OpenemsType.FLOAT)// 0x0018
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HEAT_CONTROL_SETPOINT(Doc.of(OpenemsType.FLOAT)// 0x0019
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		FLOW_MASTER_BYPASS(Doc.of(OpenemsType.FLOAT)// 0x001A 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		FLOW_MASTER_SOURCE(Doc.of(OpenemsType.FLOAT)// 0x001B 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		FLOW_MASTER_SETPOINT(Doc.of(OpenemsType.FLOAT)// 0x001C 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		FLOW_MASTER_OPERATIONAL_SETPOINT(Doc.of(OpenemsType.FLOAT)// 0x001D 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HEAT_CONTROL_ENGINE_PUMP_LEVEL(Doc.of(OpenemsType.INTEGER)// 0x001E 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.PERCENT)),
		
		HEAT_CONTROL_STORAGE_PUMP_LEVEL(Doc.of(OpenemsType.INTEGER)// 0x001F 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.PERCENT)),
		
		FLOW_MASTER_PUMP_LEVEL(Doc.of(OpenemsType.INTEGER)// 0x0020 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.PERCENT)),
		
		HEAT_CONTROL_VALVE_POSITION(Doc.of(OpenemsType.INTEGER)// 0x0021 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.PERCENT)),
		
		FLOW_MASTER_VALVE_POSITION(Doc.of(OpenemsType.INTEGER)// 0x0022 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.PERCENT)),

		POWER_THERMAL(Doc.of(OpenemsType.FLOAT)// 0x0023
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.KILOWATT)),//
		
		HEAT_EXCHANGER_CONDITION(Doc.of(OpenemsType.FLOAT)// 0x0024
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.NONE)),//
		
		SEPARATION_LAYER_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x0025
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//
		
		PU_REQUESTED_POWER(Doc.of(OpenemsType.INTEGER)// 0x0026
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.WATT)),//

		PU_LGVGP_LIMIT(Doc.of(OpenemsType.INTEGER)// 0x0027
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.WATT)),//		

		PU_AIM_POWER(Doc.of(OpenemsType.INTEGER)// 0x0028
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.WATT)),//
		
		PU_STEP_POSITION(Doc.of(OpenemsType.INTEGER)// 0x0029
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.NONE)),//

		PU_MAP_PRESSURE(Doc.of(OpenemsType.INTEGER)// 0x002A
				.accessMode(AccessMode.READ_ONLY)),//
		
		PU_FUEL_STEP_POSITION(Doc.of(OpenemsType.INTEGER)// 0x002B
				.accessMode(AccessMode.READ_ONLY)),//
		
		PU_IGNITION_ANGLE(Doc.of(OpenemsType.FLOAT)// 0x002C
				.accessMode(AccessMode.READ_ONLY)),//
		
		PU_WATER_TEMPERATURE(Doc.of(OpenemsType.FLOAT)// 0x002D
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//
		
		PU_RPM(Doc.of(OpenemsType.INTEGER)// 0x002E
				.accessMode(AccessMode.READ_ONLY)),//

		L1L2_VOLTAGE(Doc.of(OpenemsType.INTEGER)// 0x002F
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		L2L3_VOLTAGE(Doc.of(OpenemsType.INTEGER)// 0x0030
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		L3L1_VOLTAGE(Doc.of(OpenemsType.INTEGER)// 0x0031
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		GRID_FREQUENCY(Doc.of(OpenemsType.FLOAT)// 0x0032
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HERTZ)),//
		
		SYSTEM_STATUS(Doc.of(OpenemsType.INTEGER)// 0x0033
				.accessMode(AccessMode.READ_ONLY)),//

		;

		private final Doc doc;

		private ChannelId(Doc doc) {
			this.doc = doc;
		}

		@Override
		public Doc doc() {
			return this.doc;
		}
	}

}
