package ecp.ems.edge.chp.api;

import org.osgi.annotation.versioning.ProviderType;

import io.openems.common.channel.PersistencePriority;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.startstop.StartStoppable;

/**
 * Represents a CHP
 * 
 * <p>
 * Documentation....
 * 
 */
@ProviderType
public interface ChpApi extends StartStoppable, OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		
		/**
		 * Active Error
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

}
