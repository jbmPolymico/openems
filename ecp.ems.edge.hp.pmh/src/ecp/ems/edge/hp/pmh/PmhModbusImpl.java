package ecp.ems.edge.hp.pmh;

//import static io.openems.edge.bridge.modbus.api.ElementToChannelConverter.SCALE_FACTOR_MINUS_1;
import static io.openems.edge.bridge.modbus.api.ElementToChannelConverter.SCALE_FACTOR_MINUS_1;

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

import io.openems.common.exceptions.OpenemsException;
import io.openems.edge.bridge.modbus.api.AbstractOpenemsModbusComponent;
import io.openems.edge.bridge.modbus.api.BridgeModbus;
import io.openems.edge.bridge.modbus.api.ModbusComponent;
import io.openems.edge.bridge.modbus.api.ModbusProtocol;
import io.openems.edge.bridge.modbus.api.element.BitsWordElement;
import io.openems.edge.bridge.modbus.api.element.DummyRegisterElement;
import io.openems.edge.bridge.modbus.api.element.SignedWordElement;
import io.openems.edge.bridge.modbus.api.element.SignedWordElementFloat;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElement;
import io.openems.edge.bridge.modbus.api.element.UnsignedWordElementFloat;
import io.openems.edge.bridge.modbus.api.task.FC16WriteRegistersTask;
import io.openems.edge.bridge.modbus.api.task.FC3ReadRegistersTask;
import io.openems.edge.bridge.modbus.api.task.Task;
import io.openems.edge.common.channel.Channel;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.common.taskmanager.Priority;

@Designate(ocd = Config.class, factory = true)
@Component(//
		name = "ecp.ems.edge.hp.pmh", //
		immediate = true, //
		configurationPolicy = ConfigurationPolicy.REQUIRE //
)
public class PmhModbusImpl extends AbstractOpenemsModbusComponent implements PmhModbus, ModbusComponent, OpenemsComponent {

	@Reference
	private ConfigurationAdmin cm;
	
	private final Logger log = LoggerFactory.getLogger(PmhModbusImpl.class);

	@Reference(policy = ReferencePolicy.STATIC, policyOption = ReferencePolicyOption.GREEDY, cardinality = ReferenceCardinality.MANDATORY)
	protected void setModbus(BridgeModbus modbus) {
		super.setModbus(modbus);
	}

	private Config config = null;

	public PmhModbusImpl() {
		super(//
				OpenemsComponent.ChannelId.values(), //
				ModbusComponent.ChannelId.values(), //
				PmhModbus.ChannelId.values() //
		);
	}

	@Activate
	private void activate(ComponentContext context, Config config) throws OpenemsException {
		if(super.activate(context, config.id(), config.alias(), config.enabled(), config.modbusUnitId(), this.cm, "Modbus",
				config.modbus_id())) {
			return;
		}
		this.config = config;
	}

	@Override
	@Deactivate
	protected void deactivate() {
		super.deactivate();
	}

	@Override
	protected ModbusProtocol defineModbusProtocol() throws OpenemsException {
		return new ModbusProtocol(this,//
				// Read parameters
				new FC3ReadRegistersTask(0, Priority.LOW,  
						m(PmhModbus.ChannelId.SYSTEM_ON,new UnsignedWordElement(0)),// 0x0000
						m(PmhModbus.ChannelId.SOFTWARE_VERSION, new UnsignedWordElement(1)), // 0x0001
						m(PmhModbus.ChannelId.DATABASE_VERSION, new UnsignedWordElement(2)), // 0x0002
						m(PmhModbus.ChannelId.WORKING_PROFILE, new UnsignedWordElement(3)), // 0x0003
						m(PmhModbus.ChannelId.LANGUAGE_SELECTION, new UnsignedWordElement(4)), // 0x0004
						m(PmhModbus.ChannelId.DHW_MODE, new UnsignedWordElement(5)), // 0x0005
						m(PmhModbus.ChannelId.HEATING_MODE, new UnsignedWordElement(6)), // 0x0006
						m(PmhModbus.ChannelId.COOLING_MODE, new UnsignedWordElement(7)), // 0x0007
						m(PmhModbus.ChannelId.AUTO_SWITCH_SOURCE, new UnsignedWordElement(8)), // 0x0008
						m(PmhModbus.ChannelId.WATER_OR_ROOM_CONTROL, new UnsignedWordElement(9)), //0x0009
						m(PmhModbus.ChannelId.AMBIENT_TEMP_START_HEATING, new UnsignedWordElement(10)), // 0x000A
						m(PmhModbus.ChannelId.AMBIENT_TEMP_START_COOLING, new UnsignedWordElement(11)), // 0x000B
						m(PmhModbus.ChannelId.DURATION_MIN_COMP_SPEED, new UnsignedWordElement(12)), // 0x000C
						m(PmhModbus.ChannelId.MAX_HEATING_SET_TEMPERATURE, new UnsignedWordElement(13)), // 0x000D
						m(PmhModbus.ChannelId.MIN_HEATING_SET_TEMPERATURE, new UnsignedWordElement(14)), // 0x000E
						m(PmhModbus.ChannelId.MAX_COOLING_SET_TEMPERATURE, new UnsignedWordElement(15)), // 0x000F
						m(PmhModbus.ChannelId.MIN_COOLING_SET_TEMPERATURE, new UnsignedWordElement(16)) // 0x0010
						),
				
				new FC3ReadRegistersTask(18, Priority.LOW,						
						m(PmhModbus.ChannelId.TIMER_ACTIVE, new UnsignedWordElement(18)), // 0x0012
						new DummyRegisterElement(19,25), // Timer paramters
						m(PmhModbus.ChannelId.DELTA_T_TO_STOP, new UnsignedWordElement(26)), // 0x001A
						m(PmhModbus.ChannelId.DELTA_T_TO_RESTART, new UnsignedWordElement(27)), // 0x001B
						m(PmhModbus.ChannelId.DELTA_T_TO_LOWER_COMP_SPEED, new UnsignedWordElement(28)), //0x001C
						m(PmhModbus.ChannelId.COOLING_SETPOINT, new UnsignedWordElement(29)), // 0x001D
						m(PmhModbus.ChannelId.HEATING_CURVE_ENABLE, new UnsignedWordElement(30)) //0x001E
						// Heat curve parameters 31-40
						),
				
				new FC3ReadRegistersTask(42, Priority.LOW,
						//new DummyRegisterElement(41), // 0x0029 No assigned value on this register
						m(PmhModbus.ChannelId.IDEAL_ROOM_TEMP_FOR_HEATING, new UnsignedWordElement(42)), // 0x002A
						m(PmhModbus.ChannelId.IDEAL_ROOM_TEMP_FOR_COOLING, new UnsignedWordElement(43)), // 0x002B
						m(PmhModbus.ChannelId.HEATING_SETPOINT_NOCURVE, new UnsignedWordElement(44)), // 0x002C
						m(PmhModbus.ChannelId.SYSTEM_1_MAX_SETPOINT, new UnsignedWordElement(45)), // 0x002D
						m(PmhModbus.ChannelId.SYSTEM_1_MIN_SETPOINT, new UnsignedWordElement(46)), // 0x002E
						m(PmhModbus.ChannelId.ANTILEGIONELLA_MODE_ENABLE, new UnsignedWordElement(47)), // 0x002F
						m(PmhModbus.ChannelId.ANTILEGIONELLA_MODE_STARTDATE, new UnsignedWordElement(48)), // 0x0030
						m(PmhModbus.ChannelId.SETPOINT_ANTILEGIONELLA, new UnsignedWordElement(49)), // 0x0031
						m(PmhModbus.ChannelId.DURATION_ANTILEGIONELLA, new UnsignedWordElement(50)), // 0x0032
						m(PmhModbus.ChannelId.MAX_DURATION_ANTILEGIONELLA, new UnsignedWordElement(51)), // 0x0033
						m(PmhModbus.ChannelId.DELTA_T_HOLIDAY_MODE_ENABLE, new UnsignedWordElement(52)), // 0x0034
						m(PmhModbus.ChannelId.DELTA_T_DHW_HOLIDAY_MODE, new UnsignedWordElement(53)), // 0x0035
						m(PmhModbus.ChannelId.DELTA_T_HEATING_HOLIDAY_MODE, new UnsignedWordElement(54)), // 0x0036
						m(PmhModbus.ChannelId.HOLIDAY_MODE_START_DATE, new UnsignedWordElement(55)), // 0x0037
						m(PmhModbus.ChannelId.HOLIDAY_MODE_END_DATE, new UnsignedWordElement(56)), // 0x0038
						m(PmhModbus.ChannelId.HEATING_BACKUP_HEATER_STATUS, new UnsignedWordElement(57)), // 0x0039
						m(PmhModbus.ChannelId.PRIORITY_AH_HBH, new UnsignedWordElement(58)), // 0x003A
						m(PmhModbus.ChannelId.HOT_WATER_BACKUP_HEATER_STATUS, new UnsignedWordElement(59)), // 0x003B
						m(PmhModbus.ChannelId.PRIORITY_AH_HWTBH, new UnsignedWordElement(60)), // 0x003C
						m(PmhModbus.ChannelId.SETTING_TO_ACTIVATE_HWTBH_IN_DHW_MODE, new UnsignedWordElement(61)), // 0x003D
						m(PmhModbus.ChannelId.TIME_TO_ACTIVATE_HWTBH_IN_DHW_MODE, new UnsignedWordElement(62)), // 0x003E
						m(PmhModbus.ChannelId.HEATING_EMERGENCY_MODE_ENABLE, new UnsignedWordElement(63)), // 0x003F
						m(PmhModbus.ChannelId.DHW_SETPOINT, new UnsignedWordElement(64)), // 0x0040
						m(PmhModbus.ChannelId.DHW_RESTART_DELTAT, new UnsignedWordElement(65)) // 0x0041
						),
						
				new FC3ReadRegistersTask(72, Priority.LOW,
						//new DummyRegisterElement(66,71), // 0x0042-0x0047 No assigned value to these registers.
						m(PmhModbus.ChannelId.DHW_STORAGE_ENABLE, new UnsignedWordElement(72)), // 0x0048
						m(PmhModbus.ChannelId.DHW_REHEATING_ENABLE, new UnsignedWordElement(73)), // 0x0049
						m(PmhModbus.ChannelId.DHW_REHEATING_SETPOINT, new UnsignedWordElement(74)), // 0x004A
						m(PmhModbus.ChannelId.DHW_REHEATING_RESTART_POINT, new UnsignedWordElement(75))// 0x004B
						// DHW timer registers 76-89
						), 
				
				new FC3ReadRegistersTask(90, Priority.LOW,
						m(PmhModbus.ChannelId.CIRCUIT2_ENABLE, new UnsignedWordElement(90)), // 0x005A
						m(PmhModbus.ChannelId.CIRCUIT2_COOLING_SETPOINT, new UnsignedWordElement(91)), // 0x005B
						m(PmhModbus.ChannelId.CIRCUIT2_HEATINGCURVE_ENABLE, new UnsignedWordElement(92)), // 0x005C
						m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT1, new UnsignedWordElement(93)), // 0x005D
						m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT2, new UnsignedWordElement(94)), // 0x005E
						m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT3, new UnsignedWordElement(95)), // 0x005F
						m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT4, new UnsignedWordElement(96)), // 0x0060
						m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT5, new UnsignedWordElement(97)), // 0x0061
						m(PmhModbus.ChannelId.CIRCUIT2_HEATING_SETPOINT, new UnsignedWordElement(98)), //0x0062
						m(PmhModbus.ChannelId.CIRCUIT2_MAX_SETPOINT, new UnsignedWordElement(99)), // 0x0063
						m(PmhModbus.ChannelId.CIRCUIT2_MIN_SETPOINT, new UnsignedWordElement(100)), // 0x0064
						m(PmhModbus.ChannelId.SLEEP_MODE_ENABLE, new UnsignedWordElement(101)), // 0x0065
						new DummyRegisterElement(102,109), // Sleep mode parameters
						m(PmhModbus.ChannelId.LOW_NOISE_ENABLE, new UnsignedWordElement(110)) // 0x006E
						//new DummyRegisterElement(111,118) // Low noise mode parameters
						),
				
				new FC3ReadRegistersTask(126, Priority.LOW,		
						//new DummyRegisterElement(119,125), // 0x0077-0x007D No assigned value to these registers.
						m(PmhModbus.ChannelId.BACKLIGHT, new UnsignedWordElement(126)), // 0x007E
						m(PmhModbus.ChannelId.BAUD_RATE, new UnsignedWordElement(127)), // 0x007F
						m(PmhModbus.ChannelId.WORD_LENGTH, new UnsignedWordElement(128)), // 0x0080
						m(PmhModbus.ChannelId.PARITY, new UnsignedWordElement(129)), // 0x0081
						m(PmhModbus.ChannelId.STOPBITS, new UnsignedWordElement(130)), // 0x0082
						new DummyRegisterElement(131,132), // 0x0083-0x0084 No assigned value to these registers.
						m(PmhModbus.ChannelId.AMBIENT_TO_START_DHW_ECO, new UnsignedWordElement(133)), // 0x0085
						m(PmhModbus.ChannelId.AMBIENT_TO_START_HEATING_ECO, new UnsignedWordElement(134)), // 0x0086
						m(PmhModbus.ChannelId.AMBIENT_TO_START_DHW_ECO_ENABLE, new UnsignedWordElement(135)), // 0x0087
						m(PmhModbus.ChannelId.AMBIENT_TO_START_HEATING_ECO_ENABLE, new UnsignedWordElement(136)), // 0x0088
						m(PmhModbus.ChannelId.WATER_PUMP_TYPE, new UnsignedWordElement(137)), // 0x0089
						new DummyRegisterElement(138), // 0x008A No assigned value to these registers.
						m(PmhModbus.ChannelId.WATER_PUMP_WORKING_MODE, new UnsignedWordElement(139)), // 0x008B
						m(PmhModbus.ChannelId.PUMP_OFF_DURATION_INTERVAL_MODE, new UnsignedWordElement(140)), // 0x008C
						m(PmhModbus.ChannelId.PUMP_ON_DURATION_INTERVAL_MODE, new UnsignedWordElement(141)), // 0x008D
						m(PmhModbus.ChannelId.BFT, new UnsignedWordElement(142)), // 0x008E
						m(PmhModbus.ChannelId.MIXINGVALVE1_STATUS, new UnsignedWordElement(143)), // 0x008F
						m(PmhModbus.ChannelId.MIXINGVALVE2_STATUS, new UnsignedWordElement(144)), // 0x0090
						new DummyRegisterElement(145,174), // 0x0091-0x00AE No assigned value to these registers.
						m(PmhModbus.ChannelId.THREE_WAY_VALVE_SWITHING_TIME, new UnsignedWordElement(175)), // 0x00AF
						m(PmhModbus.ChannelId.THREE_WAY_VALVE_POWERING_TIME, new UnsignedWordElement(176)), // 0x00B0
						new DummyRegisterElement(177,213), // 0x00B1-0x00D5  No assigned value to these registers.
						m(PmhModbus.ChannelId.PUMP_SPEED_WHEN_HEATING, new UnsignedWordElement(214)), // 0x00D6
						m(PmhModbus.ChannelId.PUMP_SPEED_WHEN_COOLING, new UnsignedWordElement(215)), // 0x00D7
						m(PmhModbus.ChannelId.PUMP_SPEED_WHEN_DHW, new UnsignedWordElement(216)) // 0x00D8
				),
				
				// Read realtime data
				new FC3ReadRegistersTask(499, Priority.LOW,
						m(new BitsWordElement(499, this) // 0x01F3
								.bit(0, PmhModbus.ChannelId.MODE_DHW) //
								.bit(1, PmhModbus.ChannelId.MODE_HEATING) //
								.bit(2, PmhModbus.ChannelId.MODE_COOLING) //
								.bit(3, PmhModbus.ChannelId.MODE_DHW_IN_PROCESS) //
								.bit(4, PmhModbus.ChannelId.MODE_HEATING_IN_PROCESS) //
								.bit(5, PmhModbus.ChannelId.MODE_COOLING_IN_PROCESS) //
								.bit(6, PmhModbus.ChannelId.MODE_TIMER_IN_PROCESS) //
						), //
						
						m(PmhModbus.ChannelId.SYSTEM_LOGIN_STATUS, new UnsignedWordElement(500)), // 0x01F4
						m(PmhModbus.ChannelId.SOFTWARE_VERSION_PARM, new UnsignedWordElement(501)), // 0x01F5
						m(PmhModbus.ChannelId.DATABASE_VERSION_PARM, new UnsignedWordElement(502)), // 0x01F6
						m(PmhModbus.ChannelId.EEPROM_VERSION, new UnsignedWordElement(503)), // 0x01F7
						m(PmhModbus.ChannelId.OUTDOORUNIT_SOFTWARE_VERSION_PARM, new UnsignedWordElement(504)), // 0x01F8
						m(PmhModbus.ChannelId.WATER_OUTLET_TEMPERATURE, new UnsignedWordElementFloat(505), SCALE_FACTOR_MINUS_1), // 0x01F9
						m(PmhModbus.ChannelId.WATER_INLET_TEMPERATURE, new UnsignedWordElementFloat(506), SCALE_FACTOR_MINUS_1), // 0x01FA
						m(PmhModbus.ChannelId.INDOOR_COIL_TEMPERATURE, new UnsignedWordElementFloat(507), SCALE_FACTOR_MINUS_1), // 0x01FB
						m(PmhModbus.ChannelId.DHW_WATER_TEMPERATURE, new UnsignedWordElementFloat(508), SCALE_FACTOR_MINUS_1), // 0x01FC
						m(PmhModbus.ChannelId.SERVICE_WATER_TEMPERATURE, new UnsignedWordElementFloat(509), SCALE_FACTOR_MINUS_1), // 0x01FD
						m(PmhModbus.ChannelId.MIXING_VALVE1_TEMPERATURE, new UnsignedWordElementFloat(510), SCALE_FACTOR_MINUS_1), // 0x01FE
						m(PmhModbus.ChannelId.MIXING_VALVE2_TEMPERATURE, new UnsignedWordElementFloat(511), SCALE_FACTOR_MINUS_1), // 0x01FF
						new DummyRegisterElement(512,513),
						m(PmhModbus.ChannelId.OPERATING_STATUS, new UnsignedWordElement(514)), // 0x0200
						m(PmhModbus.ChannelId.COMPRESSOR_SPEED, new UnsignedWordElement(515)), // 0x0201
						m(PmhModbus.ChannelId.EEV_OPENING, new UnsignedWordElement(516)), // 0x0202
						m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE, new SignedWordElementFloat(517)), // 0x0203
						new DummyRegisterElement(518,520), // Unused registers
						m(PmhModbus.ChannelId.HIGH_PRESSURE, new UnsignedWordElementFloat(521), SCALE_FACTOR_MINUS_1), // 0x0209
						m(PmhModbus.ChannelId.LOW_PRESSURE, new UnsignedWordElementFloat(522), SCALE_FACTOR_MINUS_1), // 0x020A
						m(PmhModbus.ChannelId.COMPRESSOR_DISCHARGE_TEMPERATURE, new UnsignedWordElement(523), SCALE_FACTOR_MINUS_1), // 0x020B
						m(PmhModbus.ChannelId.COMPRESSOR_SUCTION_TEMPERATURE, new UnsignedWordElement(524), SCALE_FACTOR_MINUS_1), // 0x020C
						m(PmhModbus.ChannelId.OUTDOOR_COIL_TEMPERATURE, new SignedWordElement(525), SCALE_FACTOR_MINUS_1), // 0x020D
						m(PmhModbus.ChannelId.FAN1_RPM, new UnsignedWordElement(526)), // 0x020E
						m(PmhModbus.ChannelId.FAN2_RPM, new UnsignedWordElement(527)), // 0x020F
						m(PmhModbus.ChannelId.RUNNING_CURRENT, new UnsignedWordElementFloat(528), SCALE_FACTOR_MINUS_1), // 0x0210
						m(PmhModbus.ChannelId.SUPPLY_VOLTAGE, new UnsignedWordElement(529)), // 0x0211
						m(PmhModbus.ChannelId.DEFROST_STATUS, new UnsignedWordElement(530)), // 0x0212
						m(PmhModbus.ChannelId.ROOM_TEMPERATURE, new SignedWordElementFloat(531), SCALE_FACTOR_MINUS_1), // 0x0213
						m(PmhModbus.ChannelId.WATER_FLOW_SWITCH, new UnsignedWordElement(532)), // 0x0214
						m(PmhModbus.ChannelId.UTILITY_LOCK, new UnsignedWordElement(533)), // 0x0215
						m(PmhModbus.ChannelId.COOLING_EXTERNAL, new UnsignedWordElement(534)), // 0x0216
						m(PmhModbus.ChannelId.HEATING_EXTERNAL, new UnsignedWordElement(535)), // 0x0217
						m(PmhModbus.ChannelId.HIGH_DEMAND_REQUEST, new UnsignedWordElement(536)), // 0x0218
						m(PmhModbus.ChannelId.PWM_SIGNAL, new UnsignedWordElementFloat(537), SCALE_FACTOR_MINUS_1), // 0x0219
						m(PmhModbus.ChannelId.MIXING_VALVE1_COMMAND, new UnsignedWordElementFloat(538), SCALE_FACTOR_MINUS_1), // 0x021A
						m(PmhModbus.ChannelId.MIXING_VALVE2_COMMAND, new UnsignedWordElementFloat(539), SCALE_FACTOR_MINUS_1) // 0x021B
						),
				
				
					new FC3ReadRegistersTask(540, Priority.HIGH,
						m(new BitsWordElement(540, this) // 0x021C
								.bit(0, PmhModbus.ChannelId.FAILURE1_MAINS_CURRENT) //
								.bit(1, PmhModbus.ChannelId.FAILURE1_COMPRESSOR_CURRENT) //
								.bit(2, PmhModbus.ChannelId.FAILURE1_IPM_MODULE) //
								.bit(3, PmhModbus.ChannelId.FAILURE1_COMPRESSOR_OIL_RETURN) //
								.bit(4, PmhModbus.ChannelId.FAILURE1_HIGH_PRESSURE_ON_SWITCH) //
								.bit(5, PmhModbus.ChannelId.FAILURE1_COMPRESSOR_OVERPRESSURE_SHUTDOWN) //
								.bit(6, PmhModbus.ChannelId.FAILURE1_FIRST_START_PREHEAT) //
								.bit(7, PmhModbus.ChannelId.FAILURE1_OUTDORR_GAS_DISCHARGE_TEMP) //
								.bit(8, PmhModbus.ChannelId.FAILURE1_OUTDOOR_COIL_EVAPORATOR_TEMP) //
								.bit(9, PmhModbus.ChannelId.FAILURE1_AC_HIGH_LOW_VOLTAG) //
								.bit(10, PmhModbus.ChannelId.FAILURE1_AMBIENT_TEMPERATURE) //
								.bit(11, PmhModbus.ChannelId.FAILURE1_FREQUENCY_LIMIT_BY_AMBIENT) //
								.bit(12, PmhModbus.ChannelId.FAILURE1_LOW_PRESSURE_SWITCH) //
								.bit(13, PmhModbus.ChannelId.FAILURE1_COMM_LOST_PANEL) //
								.bit(14, PmhModbus.ChannelId.FAILURE1_COMM_LOST_COMPRESSOR) //
								.bit(15, PmhModbus.ChannelId.FAILURE1_COMPRESSOR_PHASE_OPEN_SHORT) //								
						), //
						
						m(new BitsWordElement(541, this) // 0x021D
								.bit(0, PmhModbus.ChannelId.FAILURE2_COMPRESSOR_WORKING_CURRENT) //
								.bit(1, PmhModbus.ChannelId.FAILURE2_COMPRESSOR_DRIVER) //
								.bit(2, PmhModbus.ChannelId.FAILURE2_COMPRESSOR_DRIVER_VDC) //
								.bit(3, PmhModbus.ChannelId.FAILURE2_AC_CURRENT) //
								.bit(4, PmhModbus.ChannelId.FAILURE2_EEPROM) //
								.bit(5, PmhModbus.ChannelId.FAILURE2_AMBIENT_TEMPERATURE_SENSOR) //
								.bit(6, PmhModbus.ChannelId.FAILURE2_OUTDOOR_COIL_TEMPERATURE_SENSOR) //
								.bit(7, PmhModbus.ChannelId.FAILURE2_COMPRESSOR_DISCHARGE_TEMPERATURE_SENSOR) //
								.bit(8, PmhModbus.ChannelId.FAILURE2_COMPRESSOR_SUCTION_TEMPERATURE_SENSOR) //
								.bit(9, PmhModbus.ChannelId.FAILURE2_LOW_PRESSURE_SENSOR) //
								.bit(10, PmhModbus.ChannelId.FAILURE2_HIGH_PRESSURE_SENSOR) //
								.bit(11, PmhModbus.ChannelId.FAILURE2_HIGH_PRESSURE_SWITCH) //
								.bit(12, PmhModbus.ChannelId.FAILURE2_FLOW_SWITCH) //
								.bit(13, PmhModbus.ChannelId.FAILURE2_DC_FAN_MOTOR_A) //
								.bit(14, PmhModbus.ChannelId.FAILURE2_DC_FAN_MOTOR_B) //
								.bit(15, PmhModbus.ChannelId.FAILURE2_EVAPORATING_PRESSURE) //
						), //
						
						m(new BitsWordElement(542, this) // 0x021E
								.bit(0, PmhModbus.ChannelId.FAILURE3_HIGH_PRESSURE) //
								.bit(1, PmhModbus.ChannelId.FAILURE3_ROOM_TEMPERATURE_SENSOR) //
								.bit(2, PmhModbus.ChannelId.FAILURE3_TW_SENSOR) //
								.bit(3, PmhModbus.ChannelId.FAILURE3_TC_sENSOR) //
								.bit(4, PmhModbus.ChannelId.FAILURE3_WATER_OUTLET) //
								.bit(5, PmhModbus.ChannelId.FAILURE3_WATER_INLET) //
								.bit(6, PmhModbus.ChannelId.FAILURE3_INDOOR_COIL_SENSOR) //
								.bit(7, PmhModbus.ChannelId.FAILURE3_VALVE1_SENSOR) //
								.bit(8, PmhModbus.ChannelId.FAILURE3_VALVE2_SENSOR) //
								.bit(9, PmhModbus.ChannelId.FAILURE3_COMM_LOST_PANEL_2) //
								.bit(10, PmhModbus.ChannelId.FAILURE3_INDOOR_EEPROM) //
								.bit(11, PmhModbus.ChannelId.FAILURE3_PWM_SIGNAL) //
								.bit(12, PmhModbus.ChannelId.FAILURE3_VALVE1) //
								.bit(13, PmhModbus.ChannelId.FAILURE3_VALVE2) //
								.bit(14, PmhModbus.ChannelId.FAILURE3_INDOOR_ANTIFREEZE) //
								.bit(15, PmhModbus.ChannelId.FAILURE3_FLOW_SWITCH_PROTECTION) //
						), //
						
						m(new BitsWordElement(543, this) // 0x021F
								.bit(0, PmhModbus.ChannelId.FAILURE4_FLOW_SWITCH2) //
								.bit(1, PmhModbus.ChannelId.FAILURE4_INDOOR_COMM) //
								.bit(2, PmhModbus.ChannelId.FAILURE4_OUTDOOR_COMM) //
								.bit(3, PmhModbus.ChannelId.FAILURE4_LOW_OUTLET_TEMP_COOLING) //
								.bit(4, PmhModbus.ChannelId.FAILURE4_HIGH_OUTLET_TEMP_IN_HEATING) //
								.bit(5, PmhModbus.ChannelId.FAILURE4_DEFROST_FAILURE_REPEATED) //
								.bit(6, PmhModbus.ChannelId.FAILURE4_LOW_OUTLET_IN_HEATING) //
								.bit(7, PmhModbus.ChannelId.FAILURE4_FLOW_SWITCH_REPEATED) //
								.bit(8, PmhModbus.ChannelId.FAILURE4_COIL_TEMP_PROTECTION) //
								.bit(9, PmhModbus.ChannelId.FAILURE4_PREHEATING) //								
						) //
						
						),
					
					
					new FC16WriteRegistersTask(0,
							m(PmhModbus.ChannelId.SYSTEM_ON,new UnsignedWordElement(0)),// 0x0000
							new DummyRegisterElement(1,2),
							m(PmhModbus.ChannelId.WORKING_PROFILE, new UnsignedWordElement(3)), // 0x0003
							new DummyRegisterElement(4),
							m(PmhModbus.ChannelId.DHW_MODE, new UnsignedWordElement(5)), // 0x0005
							m(PmhModbus.ChannelId.HEATING_MODE, new UnsignedWordElement(6)), // 0x0006
							m(PmhModbus.ChannelId.COOLING_MODE, new UnsignedWordElement(7)), // 0x0007
							m(PmhModbus.ChannelId.AUTO_SWITCH_SOURCE, new UnsignedWordElement(8)), // 0x0008
							new DummyRegisterElement(9),
							m(PmhModbus.ChannelId.AMBIENT_TEMP_START_HEATING, new UnsignedWordElement(10)), // 0x000A
							m(PmhModbus.ChannelId.AMBIENT_TEMP_START_COOLING, new UnsignedWordElement(11)), // 0x000B
							m(PmhModbus.ChannelId.DURATION_MIN_COMP_SPEED, new UnsignedWordElement(12)), // 0x000C
							new DummyRegisterElement(13,18),
							new DummyRegisterElement(19,25), // Timer read only parameters
							m(PmhModbus.ChannelId.DELTA_T_TO_STOP, new UnsignedWordElement(26)), // 0x001A
							m(PmhModbus.ChannelId.DELTA_T_TO_RESTART, new UnsignedWordElement(27)), // 0x001B
							m(PmhModbus.ChannelId.DELTA_T_TO_LOWER_COMP_SPEED, new UnsignedWordElement(28)), //0x001C
							m(PmhModbus.ChannelId.COOLING_SETPOINT, new UnsignedWordElement(29)), // 0x001D
							m(PmhModbus.ChannelId.HEATING_CURVE_ENABLE, new UnsignedWordElement(30)), //0x001E
							new DummyRegisterElement(31,40), // Heating curve parameters - read only
							new DummyRegisterElement(41,43),
							m(PmhModbus.ChannelId.HEATING_SETPOINT_NOCURVE, new UnsignedWordElement(44)), // 0x002C
							new DummyRegisterElement(45,46),
							m(PmhModbus.ChannelId.ANTILEGIONELLA_MODE_ENABLE, new UnsignedWordElement(47)), // 0x002F
							m(PmhModbus.ChannelId.ANTILEGIONELLA_MODE_STARTDATE, new UnsignedWordElement(48)), // 0x0030
							new DummyRegisterElement(49,51),
							m(PmhModbus.ChannelId.DELTA_T_HOLIDAY_MODE_ENABLE, new UnsignedWordElement(52)), // 0x0034
							new DummyRegisterElement(53,62),
							m(PmhModbus.ChannelId.HEATING_EMERGENCY_MODE_ENABLE, new UnsignedWordElement(63)), // 0x003F
							m(PmhModbus.ChannelId.DHW_SETPOINT, new UnsignedWordElement(64)), // 0x0040
							m(PmhModbus.ChannelId.DHW_RESTART_DELTAT, new UnsignedWordElement(65)), // 0x0041
							new DummyRegisterElement(66,71), // 0x0042-0x0047 No assigned value to these registers.
							m(PmhModbus.ChannelId.DHW_STORAGE_ENABLE, new UnsignedWordElement(72)), // 0x0048
							m(PmhModbus.ChannelId.DHW_REHEATING_ENABLE, new UnsignedWordElement(73)) // 0x0049
							),
							// registers 74,75) are not used
							// registers 76-89 are handled by enable disable function
							
					new FC16WriteRegistersTask(90,
							m(PmhModbus.ChannelId.CIRCUIT2_ENABLE, new UnsignedWordElement(90)), // 0x005A
							m(PmhModbus.ChannelId.CIRCUIT2_COOLING_SETPOINT, new UnsignedWordElement(91)), // 0x005B
							m(PmhModbus.ChannelId.CIRCUIT2_HEATINGCURVE_ENABLE, new UnsignedWordElement(92)), // 0x005C
							new DummyRegisterElement(93,97), // Heat curve 2 setpoint values.
							m(PmhModbus.ChannelId.CIRCUIT2_HEATING_SETPOINT, new UnsignedWordElement(98)), //0x0062
							new DummyRegisterElement(99,100),
							m(PmhModbus.ChannelId.SLEEP_MODE_ENABLE, new UnsignedWordElement(101)), // 0x0065
							new DummyRegisterElement(102,109), // Sleep mode parameters
							m(PmhModbus.ChannelId.LOW_NOISE_ENABLE, new UnsignedWordElement(110)) // 0x006E
							//new DummyRegisterElement(111,118), // Low noise parameters
							//new DummyRegisterElement(119,132), // 0x0077-0x007D No assigned value to these registers.
							),
					
					new FC16WriteRegistersTask(133,							
							m(PmhModbus.ChannelId.AMBIENT_TO_START_DHW_ECO, new UnsignedWordElement(133)), // 0x0085
							m(PmhModbus.ChannelId.AMBIENT_TO_START_HEATING_ECO, new UnsignedWordElement(134)), // 0x0086
							m(PmhModbus.ChannelId.AMBIENT_TO_START_DHW_ECO_ENABLE, new UnsignedWordElement(135)), // 0x0087
							m(PmhModbus.ChannelId.AMBIENT_TO_START_HEATING_ECO_ENABLE, new UnsignedWordElement(136)) // 0x0088
							) 
				
				
				);
	}
	
	/**
	 * Handle timer modbus elements
	 * 
	 */
	protected synchronized void handleTimer() {
		
		final Task ReadTask = new FC3ReadRegistersTask(19, Priority.LOW,				
				m(PmhModbus.ChannelId.SUNDAY_TIMER, new UnsignedWordElement(19)), // 0x0013
				m(PmhModbus.ChannelId.MONDAY_TIMER, new UnsignedWordElement(20)), // 0x0014
				m(PmhModbus.ChannelId.TUESDAY_TIMER, new UnsignedWordElement(21)), // 0x0015
				m(PmhModbus.ChannelId.WEDNESDAY_TIMER, new UnsignedWordElement(22)), // 0x0016
				m(PmhModbus.ChannelId.THURSDAY_TIMER, new UnsignedWordElement(23)), // 0x0017
				m(PmhModbus.ChannelId.FRIDAY_TIMER, new UnsignedWordElement(24)), // 0x0018
				m(PmhModbus.ChannelId.SATURDAY_TIMER, new UnsignedWordElement(25)) // 0x0019 */
				);
				
		Channel<Integer> timerEnabled = this.channel(PmhModbus.ChannelId.TIMER_ACTIVE);
		
		var timerEnableState = timerEnabled.value(); 
		
		if (timerEnableState.get() == 1) {

			try {
				this.getModbusProtocol().addTask(ReadTask);
				} catch (OpenemsException e) {
					this.logError(this.log, "Unable to enableTimer:  " + e.getMessage());				
				}
		} else if(! (timerEnableState.get() == 1)) { // Remove elements from modbus Protocol
			
			try {
				this.getModbusProtocol().removeTask(ReadTask);
			} catch (OpenemsException e) {
				this.logError(this.log, "Unable to disableTimer:  " + e.getMessage());				
			}
			
		}
	}
	
	/**
	 * Modifying modbus protocol by adding heat curve points
	 * 
	 * @param heatingCurveState
	 * @throws OpenemsException
	 */
	private synchronized void handleHeatCurve1Protocol(boolean heatingCurveState) throws OpenemsException {
		final Task ReadTask = new FC3ReadRegistersTask(31, Priority.LOW,
				m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE1, new SignedWordElement(31)), //0x001F
				m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE2, new SignedWordElement(32)), //0x0020
				m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE3, new SignedWordElement(33)), //0x0021
				m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE4, new SignedWordElement(34)), //0x0022
				m(PmhModbus.ChannelId.AMBIENT_TEMPERATURE5, new SignedWordElement(35)), //0x0023
				m(PmhModbus.ChannelId.SETPOINT1, new UnsignedWordElement(36)), // 0x0024
				m(PmhModbus.ChannelId.SETPOINT2, new UnsignedWordElement(37)), // 0x0025
				m(PmhModbus.ChannelId.SETPOINT3, new UnsignedWordElement(38)), // 0x0026
				m(PmhModbus.ChannelId.SETPOINT4, new UnsignedWordElement(39)), // 0x0027
				m(PmhModbus.ChannelId.SETPOINT5, new UnsignedWordElement(40)) // 0x0028
			);
		
		if (heatingCurveState) {
			try {
				this.getModbusProtocol().addTask(ReadTask);
			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to add heat curve 1 to protocol: " + e.getMessage());
				}
		} else if (!heatingCurveState) {
			try {
				this.getModbusProtocol().removeTask(ReadTask);
			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to add heat curve 1 from protocol: " + e.getMessage());
			}
		}
		
		
	}
	
	/**
	 *  Calling Modbus protocol modifier when heating curve is enabled/disabled 
	 */
	protected synchronized void handleHeatCurve1() {
		Channel<Integer> heatCurve1Enabled = this.channel(PmhModbus.ChannelId.HEATING_CURVE_ENABLE_DEBUG);
		
		var heatingCurve1State = (heatCurve1Enabled.value().get() == 1);
		
		try {
			handleHeatCurve1Protocol(heatingCurve1State);
		} catch (OpenemsException e) {
			this.logError(this.log, "Heat curve change failed");
		}
	}
	
	/**
	 * Modifying modbus protocol by adding circuit 2 heat curve points
	 * 
	 * @param heatingCurveState
	 * @throws OpenemsException
	 */
	private synchronized void handleHeatCurve2Protocol(boolean heatingCurveState) throws OpenemsException {
		final Task ReadTask = new FC3ReadRegistersTask(93, Priority.LOW,
				m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT1, new UnsignedWordElement(93)), // 0x005D
				m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT2, new UnsignedWordElement(94)), // 0x005E
				m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT3, new UnsignedWordElement(95)), // 0x005F
				m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT4, new UnsignedWordElement(96)), // 0x0060
				m(PmhModbus.ChannelId.CIRCUIT2_SETPOINT5, new UnsignedWordElement(97))  // 0x0061
			);
		
		
		if (heatingCurveState) {
			try {				
				this.getModbusProtocol().addTask(ReadTask);
			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to add heat curve 2 to protocol: " + e.getMessage());
			}
		} else if (!heatingCurveState) {
			try {
				this.getModbusProtocol().removeTask(ReadTask);
			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to add heat curve 2 from protocol: " + e.getMessage());
			}
		}
		
		
	}
	
	/**
	 *  Calling Modbus protocol modifier when heating curve for circuit 2 is enabled/disabled 
	 */
	protected synchronized void handleHeatCurve2() {
		Channel<Integer> heatCurve2Enabled = this.channel(PmhModbus.ChannelId.CIRCUIT2_HEATINGCURVE_ENABLE_DEBUG);
		
		var heatingCurve2State = (heatCurve2Enabled.value().get() == 1);
		
		try {
			handleHeatCurve2Protocol(heatingCurve2State);
		} catch (OpenemsException e) {
			this.logError(this.log, "Heat curve 2 change failed");
		}
	}
	
	
	/**
	 * Modifying modbus protocol by adding or removing Sleeping mode elements
	 * 
	 * @param sleepingModeState
	 * @throws OpenemsException
	 */
	private synchronized void handleSleepingModeProtocol(boolean sleepingModeState) throws OpenemsException {
		
		final Task WriteTask = new FC16WriteRegistersTask(102,
				m(PmhModbus.ChannelId.SLEEP_MODE_DELTA_T, new UnsignedWordElement(102)), // 0x0066
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_SUNDAY, new UnsignedWordElement(103)), //0x0067
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_MONDAY, new UnsignedWordElement(104)), //0x0068
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_TUESDAY, new UnsignedWordElement(105)), //0x0069
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_WEDNESDAY, new UnsignedWordElement(106)), //0x006A
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_THURSDAY, new UnsignedWordElement(107)), //0x006B
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_FRIDAY, new UnsignedWordElement(108)), //0x006C
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_SATURDAY, new UnsignedWordElement(109)) //0x006D
				);
		
		final Task ReadTask = new FC3ReadRegistersTask(102, Priority.LOW,
				m(PmhModbus.ChannelId.SLEEP_MODE_DELTA_T, new UnsignedWordElement(102)), // 0x0066
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_SUNDAY, new UnsignedWordElement(103)), //0x0067
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_MONDAY, new UnsignedWordElement(104)), //0x0068
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_TUESDAY, new UnsignedWordElement(105)), //0x0069
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_WEDNESDAY, new UnsignedWordElement(106)), //0x006A
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_THURSDAY, new UnsignedWordElement(107)), //0x006B
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_FRIDAY, new UnsignedWordElement(108)), //0x006C
				m(PmhModbus.ChannelId.SLEEP_MODE_TIMER_SATURDAY, new UnsignedWordElement(109)) //0x006D
				);
		
		
		
		if (sleepingModeState) {
			try {
				this.getModbusProtocol().addTask(ReadTask);
				this.getModbusProtocol().addTask(WriteTask);
			
			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to add sleeping mode to protocol: " + e.getMessage());
			}
		} else if (!sleepingModeState) {
			try {
				this.getModbusProtocol().removeTask(ReadTask);
				this.getModbusProtocol().removeTask(WriteTask);

			} catch (OpenemsException e) {
				this.logError(this.log,  "Unable to remove sleeping mode from protocol: " + e.getMessage());
			}
		}
	}
	
	
	/**
	 * Handle sleeping mode 
	 */
	protected synchronized void handleSleepMode() {
		Channel<Integer> sleepingModeEnabled = this.channel(PmhModbus.ChannelId.SLEEP_MODE_ENABLE_DEBUG);
		
		var sleepingModeState = (sleepingModeEnabled.value().get() == 1);
		
		try {
			handleSleepingModeProtocol(sleepingModeState);
		} catch (OpenemsException e) {
			this.logError(this.log, "Sleep mode change failed");
		}
	}
	
	/**
	 * Modifying modbus protocol by enabling/disabling parameters for low noise mode.
	 * 
	 * @param lowNoiseModeState
	 * @throws OpenemsException
	 */
	private synchronized void handleLowNoiseModeProtocol(boolean lowNoiseModeState) throws OpenemsException {
		System.out.println("Number of tasks pre handling: " +  this.getModbusProtocol().getTaskManager().countTasks());
		System.out.println(lowNoiseModeState);
		
		final Task WriteTask = new FC16WriteRegistersTask(111,
				m(PmhModbus.ChannelId.LOW_NOISE_DELTA_T, new UnsignedWordElement(111)), // 0x006F
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_SUNDAY, new UnsignedWordElement(112)), // 0x0070
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_MONDAY, new UnsignedWordElement(113)), // 0x0071
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_TUESDAY, new UnsignedWordElement(114)), // 0x0072
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_WEDNESDAY, new UnsignedWordElement(115)), // 0x0073
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_THURSDAY, new UnsignedWordElement(116)), // 0x0074
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_FRIDAY, new UnsignedWordElement(117)), // 0x0075
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_SATURDAY, new UnsignedWordElement(118)) // 0x0076
				);
		
		final Task ReadTask = new FC3ReadRegistersTask(111, Priority.HIGH,
				m(PmhModbus.ChannelId.LOW_NOISE_DELTA_T, new UnsignedWordElement(111)), // 0x006F
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_SUNDAY, new UnsignedWordElement(112)), // 0x0070
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_MONDAY, new UnsignedWordElement(113)), // 0x0071
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_TUESDAY, new UnsignedWordElement(114)), // 0x0072
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_WEDNESDAY, new UnsignedWordElement(115)), // 0x0073
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_THURSDAY, new UnsignedWordElement(116)), // 0x0074
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_FRIDAY, new UnsignedWordElement(117)), // 0x0075
				m(PmhModbus.ChannelId.LOW_NOISE_TIMER_SATURDAY, new UnsignedWordElement(118)) // 0x0076
				);
		
		
		if (lowNoiseModeState) {
			try {
				this.getModbusProtocol().addTask(ReadTask);
				this.getModbusProtocol().addTask(WriteTask);
				} catch (OpenemsException e) {
					this.logError(this.log,  "Unable to add low noise mode to protocol: " + e.getMessage());
				} 
		 } else if (!lowNoiseModeState) {
			try {
				this.getModbusProtocol().removeTask(ReadTask);
				this.getModbusProtocol().removeTask(WriteTask);
				} catch (OpenemsException e) {
					this.logError(this.log,  "Unable to remove low noise mode from protocol: " + e.getMessage());
				}
		 }
		
		System.out.println("Number of tasks post handling: " +  this.getModbusProtocol().getTaskManager().countTasks());
		System.out.println("Modbus debuglog " + this.getBridgeModbus().debugLog());
	};
	
	
	/**
	 * Handle low noise mode 
	 */
	protected synchronized void handleLowNoiseMode() {
		Channel<Integer> lowNoiseModeEnabled = this.channel(PmhModbus.ChannelId.LOW_NOISE_ENABLE_DEBUG);
		
		var lowNoiseModeState = (lowNoiseModeEnabled.value().get() == 1);
		
		try {
			handleLowNoiseModeProtocol(lowNoiseModeState);
		} catch (OpenemsException e) {
			this.logError(this.log, "Low noise mode change failed");
		}
	}
	
	/**
	 * Modifying modbus protocol by enabling/disabling parameters for DHW storage timer.
	 * 
	 * @param dhwStorageTimerState
	 * @throws OpenemsException
	 */
	private synchronized void handleDhwStorageTimerProtocol(boolean dhwStorageTimerState) throws OpenemsException {
		final Task ReadTask = new FC3ReadRegistersTask(76, Priority.LOW,
				m(PmhModbus.ChannelId.DHW_TIMER_SUNDAY, new UnsignedWordElement(76)),// 0x004C
				m(PmhModbus.ChannelId.DHW_TIMER_MONDAY, new UnsignedWordElement(77)),// 0x004D
				m(PmhModbus.ChannelId.DHW_TIMER_TUESDAY, new UnsignedWordElement(78)),// 0x004E
				m(PmhModbus.ChannelId.DHW_TIMER_WEDNESDAY, new UnsignedWordElement(79)),// 0x004F
				m(PmhModbus.ChannelId.DHW_TIMER_THURSDAY, new UnsignedWordElement(80)),// 0x0050
				m(PmhModbus.ChannelId.DHW_TIMER_FRIDAY, new UnsignedWordElement(81)),// 0x0051
				m(PmhModbus.ChannelId.DHW_TIMER_SATURDAY, new UnsignedWordElement(82)),// 0x0052
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_SUNDAY, new UnsignedWordElement(83)),// 0x0053
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_MONDAY, new UnsignedWordElement(84)),// 0x0054
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_TUESDAY, new UnsignedWordElement(85)),// 0x0055
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_WEDNESDAY, new UnsignedWordElement(86)),// 0x0056
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_THURSDAY, new UnsignedWordElement(87)),// 0x0057
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_FRIDAY, new UnsignedWordElement(88)),// 0x0058
				m(PmhModbus.ChannelId.DHW_REHEATING_TIMER_SATURDAY, new UnsignedWordElement(89))// 0x0059
				);
		
		final Task WriteTask = new FC16WriteRegistersTask(76,
				m(PmhModbus.ChannelId.DHW_TIMER_SUNDAY, new UnsignedWordElement(76)),// 0x004C
				m(PmhModbus.ChannelId.DHW_TIMER_MONDAY, new UnsignedWordElement(77)),// 0x004D
				m(PmhModbus.ChannelId.DHW_TIMER_TUESDAY, new UnsignedWordElement(78)),// 0x004E
				m(PmhModbus.ChannelId.DHW_TIMER_WEDNESDAY, new UnsignedWordElement(79)),// 0x004F
				m(PmhModbus.ChannelId.DHW_TIMER_THURSDAY, new UnsignedWordElement(80)),// 0x0050
				m(PmhModbus.ChannelId.DHW_TIMER_FRIDAY, new UnsignedWordElement(81)),// 0x0051
				m(PmhModbus.ChannelId.DHW_TIMER_SATURDAY, new UnsignedWordElement(82))// 0x0052)
				);
		
		if (dhwStorageTimerState) {
			try {
				this.getModbusProtocol().addTask(ReadTask);
				this.getModbusProtocol().addTask(WriteTask);
				} catch (OpenemsException e) {
					this.logError(this.log,  "Unable to add DHW storage timer to protocol: " + e.getMessage());
				} 
		 } else if (!dhwStorageTimerState) {
			try {
				this.getModbusProtocol().removeTask(ReadTask);
				this.getModbusProtocol().removeTask(WriteTask);
				} catch (OpenemsException e) {
					this.logError(this.log,  "Unable to remove DHW storage timer from protocol: " + e.getMessage());
				}
		 }
	}
	
	/**
	 *  Handle activation/de-activation of DHW storage timer
	 */
	protected synchronized void handleDhwStorageTimer() {
		Channel<Integer> dhwStorageTimerEnabled = this.channel(PmhModbus.ChannelId.DHW_STORAGE_ENABLE_DEBUG);
		Channel<Integer> dhwStorageTimerReheatingEnabled = this.channel(PmhModbus.ChannelId.DHW_REHEATING_ENABLE_DEBUG);
		
		
		var dhwStorageTimerState = (dhwStorageTimerEnabled.value().get() == 1) || (dhwStorageTimerReheatingEnabled.value().get() == 1);
		
		try {
			handleDhwStorageTimerProtocol(dhwStorageTimerState);
		} catch (OpenemsException e) {
			this.logError(this.log, "DHW storage timer change failed");
		}
	}

	@Override
	public String debugLog() {
		return "PMH Modbus debug";
	}
}
