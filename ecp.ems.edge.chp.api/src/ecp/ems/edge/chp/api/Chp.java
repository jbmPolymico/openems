package ecp.ems.edge.chp.api;

import org.osgi.annotation.versioning.ProviderType;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.PersistencePriority;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.channel.IntegerReadChannel;
import io.openems.edge.common.channel.value.Value;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.modbusslave.ModbusSlaveNatureTable;
import io.openems.edge.common.modbusslave.ModbusType;
import io.openems.edge.common.startstop.StartStoppable;

/**
 * Represents a CHP
 * 
 * <p>
 * Documentation....
 * 
 */
@ProviderType
public interface Chp extends StartStoppable, OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		
		/**
		 * VPP Mode
		 * 
		 * FC10, 0x00
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Integer
		 * <li>Unit: None
		 * </ul>
		 */
		VPP_MODE(Doc.of(OpenemsType.INTEGER)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * CHP Load Level
		 * 
		 * FC10, 0x01
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Integer
		 * <li>Unit: Percentage
		 * <li>Range: 50..100
		 * </ul>
		 */
		CHP_LOAD_LEVEL(Doc.of(OpenemsType.INTEGER)//
				.unit(Unit.PERCENT)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Active Error
		 * 
		 * FC01, 0x0000
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: BOOL
		 * <li>Unit: NONE
		 * </ul>
		 */
		ERROR_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/** 
		 * Running active
		 * 
		 * FC01, 0x0001
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: BOOL
		 * <li>Unit: NONE
		 * </ul>
		 */
		RUNNING_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Ready active
		 * 
		 * FC01, 0x0002
		 * 
		 * <ul> 
		 * <li>Interface: CHP
		 * <li>Type: BOOL
		 * <li>Unit: NONE
		 * </ul>
		 */
		READY_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Storage sequence found
		 * 
		 * FC01, 0x0005
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: BOOL
		 * <li>Unit: NONE
		 * </ul>
		 */
		STORAGE_SEQUENCE_FOUND(Doc.of(OpenemsType.BOOLEAN)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * System status
		 * 
		 * FC04, 0x0033
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: INTEGER
		 * <li>Unit: None
		 * </ul>
		 */
		SYSTEM_STATUS(Doc.of(OpenemsType.INTEGER)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Error Code
		 * 
		 * FC04, 0x0012
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: INTEGER
		 * <li>UNIT: NONE
		 * </ul>
		 */
		
		ERROR_CODE(Doc.of(OpenemsType.INTEGER)//
				.unit(Unit.NONE)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Electric Power Production
		 * 
		 * FC04, 0x0006
		 * 
		 * <ul> 
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: kW
		 * </ul>
		 */
		POWER_ELECTRICAL(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.KILOWATT)//
				.persistencePriority(PersistencePriority.HIGH)), //
		
		/**
		 * Thermal Power Production
		 * 
		 * FC04, 0x0023
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: kW
		 * </ul>
		 */
		POWER_THERMAL(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.KILOWATT)//
				.persistencePriority(PersistencePriority.HIGH)),//
		
		/**
		 * Hours to service
		 * 
		 * FC04, 0x0011
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: hour
		 * </ul>
		 */
		HOURS_TO_SERVICE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.HOUR)//
				.persistencePriority(PersistencePriority.HIGH)),//
		
		/**
		 * Sum Running Hours
		 * 
		 * FC04, 0x0010
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: FLOAT
		 * <li>Unit: Hours
		 * </ul>
		 */
		SUM_RUNNING_HOURS(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.HOUR)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Cooling Capacity
		 * 
		 * FC04, 0x0007
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: INTEGER
		 * <li>Unit: Percentage
		 * <li>Range: 0..100
		 * </ul>
		 */
		COOLING_CAPACITY(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.PERCENT)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Storage Top Temperature
		 * 
		 * FC04, 0x0000
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		STORAGE_TOP_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Storage Bottom Temperature
		 * 
		 * FC04, 0x0001
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		STORAGE_BOTTOM_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Separation Layer Temperature
		 * 
		 * FC04, 0x0025
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		SEPARATION_LAYER_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Flow Forward Temperature
		 * 
		 * FC04, 0x0002
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		FLOW_FORWARD_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Flow RETURN Temperature
		 * 
		 * FC04, 0x0003
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		FLOW_RETURN_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		
		/**
		 * CHP TO Net Temperature
		 * 
		 * FC04, 0x0004
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Degree Celsius
		 * </ul>
		 */
		CHP_TO_NET_TEMPERATURE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.DEGREE_CELSIUS)
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * L1-L2 voltage
		 * 
		 * FC04, 0x002F
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Voltage
		 * </ul>
		 */
		L1L2_VOLTAGE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.VOLT)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * L2-L3 voltage
		 * 
		 * FC04, 0x0030
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Voltage
		 * </ul>
		 */
		L2L3_VOLTAGE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.VOLT)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * L3-L1 voltage
		 * 
		 * FC04, 0x0031
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Voltage
		 * </ul>
		 */
		L3L1_VOLTAGE(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.VOLT)//
				.persistencePriority(PersistencePriority.HIGH)),
		
		/**
		 * Grid Frequency
		 * 
		 * FC04, 0x0032
		 * 
		 * <ul>
		 * <li>Interface: CHP
		 * <li>Type: Float
		 * <li>Unit: Hertz
		 * </ul>
		 */
		GRID_FREQUENCY(Doc.of(OpenemsType.FLOAT)//
				.unit(Unit.HERTZ)//
				.persistencePriority(PersistencePriority.HIGH))
		
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
	
	/**
	 * Used for Modbus/RTU Api Controller. Provides a Modbus table for the Channels
	 * of this Component.
	 *
	 * @param accessMode filters the Modbus-Records that should be shown
	 * @return the {@link ModbusSlaveNatureTable}
	 * 
	 */
	public static ModbusSlaveNatureTable getModbusSlaveNatureTable(AccessMode accesMode) {
		return ModbusSlaveNatureTable.of(Chp.class, accesMode, 100)//
				.channel(0, ChannelId.VPP_MODE, ModbusType.UINT16)//
				.channel(1, ChannelId.CHP_LOAD_LEVEL, ModbusType.UINT16)//
				.channel(2, ChannelId.ERROR_ACTIVE, ModbusType.UINT16)//
				.channel(3, ChannelId.RUNNING_ACTIVE, ModbusType.UINT16)//
				.channel(4, ChannelId.READY_ACTIVE, ModbusType.UINT16)//
				.channel(5, ChannelId.STORAGE_SEQUENCE_FOUND, ModbusType.UINT16)//
				.channel(6, ChannelId.SYSTEM_STATUS, ModbusType.UINT16)//
				.channel(7, ChannelId.ERROR_CODE, ModbusType.UINT16)//
				.channel(8, ChannelId.POWER_ELECTRICAL, ModbusType.UINT16)//
				.channel(9, ChannelId.POWER_THERMAL, ModbusType.UINT16)//
				.channel(10, ChannelId.HOURS_TO_SERVICE, ModbusType.UINT16)//
				.channel(11, ChannelId.SUM_RUNNING_HOURS, ModbusType.UINT16)//
				.channel(12, ChannelId.COOLING_CAPACITY, ModbusType.UINT16)//
				.channel(13, ChannelId.STORAGE_TOP_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(15, ChannelId.STORAGE_BOTTOM_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(17, ChannelId.SEPARATION_LAYER_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(19, ChannelId.FLOW_FORWARD_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(21, ChannelId.FLOW_RETURN_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(23, ChannelId.CHP_TO_NET_TEMPERATURE, ModbusType.FLOAT32)//
				.channel(25, ChannelId.L1L2_VOLTAGE, ModbusType.UINT16)//
				.channel(26, ChannelId.L2L3_VOLTAGE, ModbusType.UINT16)//
				.channel(27, ChannelId.L3L1_VOLTAGE, ModbusType.UINT16)//
				.channel(28, ChannelId.GRID_FREQUENCY, ModbusType.UINT16)//				
				.build();
	}
	
	/**** VPP Mode ****/
	/**
	 * Gets the Channel for {@link ChannelId#VPP_MODE}.
	 *
	 * @return the Channel
	 */
	public default IntegerReadChannel getVppModeChannel() {
		return this.channel(ChannelId.VPP_MODE);
	}

	/**
	 * Gets the VPP Mode, range 0..6 . See {@link ChannelId#VPP_MODE}.
	 *
	 * @return the Channel {@link Value}
	 */
	public default Value<Integer> getVppMode() {
		return this.getVppModeChannel().value();
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#VPP_MODE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setVppMode(Integer value) {
		this.getVppModeChannel().setNextValue(value);
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#VPP_MODE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setVppMode(int value) {
		this.getVppModeChannel().setNextValue(value);
	}
	

	/**** CHP LOAD LEVEL ****/
	/**
	 * Gets the Channel for {@link ChannelId#CHP_LOAD_LEVEL}.
	 *
	 * @return the Channel
	 */
	public default IntegerReadChannel getLoadLevelChannel() {
		return this.channel(ChannelId.CHP_LOAD_LEVEL);
	}

	/**
	 * Gets the Load Level [%], range 0..100 % . See {@link ChannelId#CHP_LOAD_LEVEL}.
	 *
	 * @return the Channel {@link Value}
	 */
	public default Value<Integer> getLoadLevelMode() {
		return this.getLoadLevelChannel().value();
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#CHP_LOAD_LEVEL} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setLoadLevel(Integer value) {
		this.getLoadLevelChannel().setNextValue(value);
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#CHP_LOAD_LEVEL} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setLoadLevel(int value) {
		this.getLoadLevelChannel().setNextValue(value);
	}
	 

	/**** Error Active ****/
	/**
	 * Gets the Channel for {@link ChannelId#ERROR_ACTIVE}.
	 *
	 * @return the Channel
	 */
	public default IntegerReadChannel getErrorActiveChannel() {
		return this.channel(ChannelId.ERROR_ACTIVE);
	}

	/**
	 * Gets the status of Error, range boolean . See {@link ChannelId#ERROR_ACTIVE}.
	 *
	 * @return the Channel {@link Value}
	 */
	public default Value<Integer> getErrorActive() {
		return this.getErrorActiveChannel().value();
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#ERROR_ACTIVE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setErrorActive(Integer value) {
		this.getErrorActiveChannel().setNextValue(value);
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#ERROR_ACTIVE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setErrorActive(int value) {
		this.getErrorActiveChannel().setNextValue(value);
	}
	
	
	/**** Running Active ****/
	/**
	 * Gets the Channel for {@link ChannelId#RUNNING_ACTIVE}.
	 *
	 * @return the Channel
	 */
	public default IntegerReadChannel getRunningActiveChannel() {
		return this.channel(ChannelId.RUNNING_ACTIVE);
	}

	/**
	 * Gets the status of Error, range boolean . See {@link ChannelId#ERROR_ACTIVE}.
	 *
	 * @return the Channel {@link Value}
	 */
	public default Value<Integer> getRunningActive() {
		return this.getRunningActiveChannel().value();
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#ERROR_ACTIVE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setRunningActive(Integer value) {
		this.getRunningActiveChannel().setNextValue(value);
	}

	/**
	 * Internal method to set the 'nextValue' on {@link ChannelId#ERROR_ACTIVE} Channel.
	 *
	 * @param value the next value
	 */
	public default void _setRunningActive(int value) {
		this.getRunningActiveChannel().setNextValue(value);
	}
	
	
//	/**
//	 * Generates a default DebugLog message for {@link Battery} implementations with
//	 * a State-Machine.
//	 * 
//	 * @param battery      the {@link Battery}
//	 * @param stateMachine the actual StateMachine (extends
//	 *                     {@link AbstractStateMachine})
//	 * @return a debug log String
//	 */
//	public static String generateDebugLog(Battery battery, AbstractStateMachine<?, ?> stateMachine) {
//		return new StringBuilder() //
//				.append(stateMachine.debugLog()) //
//				.append("|SoC:").append(battery.getSoc()) //
//				.append("|Actual:").append(battery.getVoltage()) //
//				.append(";").append(battery.getCurrent()) //
//				.append("|Charge:").append(battery.getChargeMaxVoltage()) //
//				.append(";").append(battery.getChargeMaxCurrent()) //
//				.append("|Discharge:").append(battery.getDischargeMinVoltage()) //
//				.append(";").append(battery.getDischargeMaxCurrent()) //
//				.toString();
//	}	

}
