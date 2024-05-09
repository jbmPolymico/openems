package ecp.ems.edge.hp.pmh;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;

public interface PmhModbus extends OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		
		// ==== Parameters for read/write FC03/FC06/FC16 ====
		SYSTEM_ON_DEBUG(Doc.of(OpenemsType.INTEGER)),// 0x0000
		SYSTEM_ON(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.READ_WRITE) //
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.SYSTEM_ON_DEBUG)), //
		/*SYSTEM_ON_ACTUAL(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),*/
		
		WORKING_PROFILE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0003
		WORKING_PROFILE(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.READ_WRITE) //
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.WORKING_PROFILE_DEBUG)), //
		
		DHW_MODE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0005
		DHW_MODE(Doc.of(OpenemsType.INTEGER) //
				.accessMode(AccessMode.READ_WRITE) //
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.DHW_MODE_DEBUG)), //
		
		HEATING_MODE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0006
		HEATING_MODE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(HEATING_MODE_DEBUG)),
		
		
		COOLING_MODE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0007
		COOLING_MODE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(COOLING_MODE_DEBUG)),
		
		AUTO_SWITCH_SOURCE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0008
		AUTO_SWITCH_SOURCE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(AUTO_SWITCH_SOURCE_DEBUG)),
		
		AMBIENT_TEMP_START_HEATING_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x000A
		AMBIENT_TEMP_START_HEATING(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TEMP_START_HEATING_DEBUG)),
		
		AMBIENT_TEMP_START_COOLING_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x000B
		AMBIENT_TEMP_START_COOLING(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TEMP_START_COOLING_DEBUG)),
		
		DURATION_MIN_COMP_SPEED_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x000C
		DURATION_MIN_COMP_SPEED(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.MINUTE)
				.onChannelSetNextWriteMirrorToDebugChannel(DURATION_MIN_COMP_SPEED_DEBUG)),
		
		DELTA_T_TO_STOP_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x001A
		DELTA_T_TO_STOP(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(DELTA_T_TO_STOP_DEBUG)),
		
		DELTA_T_TO_RESTART_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x001B
		DELTA_T_TO_RESTART(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(DELTA_T_TO_RESTART_DEBUG)),
		
		DELTA_T_TO_LOWER_COMP_SPEED_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x001C
		DELTA_T_TO_LOWER_COMP_SPEED(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(DELTA_T_TO_LOWER_COMP_SPEED_DEBUG)),
		
		COOLING_SETPOINT_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x001D
		COOLING_SETPOINT(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(COOLING_SETPOINT_DEBUG)),
		
		HEATING_CURVE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x001E
		HEATING_CURVE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(HEATING_CURVE_ENABLE_DEBUG)),
		
		HEATING_SETPOINT_NOCURVE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x002C
		HEATING_SETPOINT_NOCURVE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(HEATING_SETPOINT_NOCURVE_DEBUG)),
		
		ANTILEGIONELLA_MODE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x002F
		ANTILEGIONELLA_MODE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(ANTILEGIONELLA_MODE_ENABLE_DEBUG)),
		
		ANTILEGIONELLA_MODE_STARTDATE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0030
		ANTILEGIONELLA_MODE_STARTDATE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(ANTILEGIONELLA_MODE_STARTDATE_DEBUG)),
		
		DELTA_T_HOLIDAY_MODE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0034
		DELTA_T_HOLIDAY_MODE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(DELTA_T_HOLIDAY_MODE_ENABLE_DEBUG)),
		
		HEATING_EMERGENCY_MODE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x003F
		HEATING_EMERGENCY_MODE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(HEATING_EMERGENCY_MODE_ENABLE_DEBUG)),
		
		DHW_SETPOINT_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0040
		DHW_SETPOINT(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_SETPOINT_DEBUG)),
		
		DHW_RESTART_DELTAT_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0041
		DHW_RESTART_DELTAT(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_RESTART_DELTAT_DEBUG)),
		
		DHW_STORAGE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0048
		DHW_STORAGE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_STORAGE_ENABLE_DEBUG)),
		
		DHW_REHEATING_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0049
		DHW_REHEATING_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_REHEATING_ENABLE_DEBUG)),
		
		DHW_TIMER_SUNDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x004C
		DHW_TIMER_SUNDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_SUNDAY_DEBUG)),
		
		DHW_TIMER_MONDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x004D
		DHW_TIMER_MONDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_MONDAY_DEBUG)),
		
		DHW_TIMER_TUESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x004E
		DHW_TIMER_TUESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_TUESDAY_DEBUG)),
		
		DHW_TIMER_WEDNESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x004F
		DHW_TIMER_WEDNESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_WEDNESDAY_DEBUG)),
		
		DHW_TIMER_THURSDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0050
		DHW_TIMER_THURSDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_THURSDAY_DEBUG)),
		
		DHW_TIMER_FRIDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0051
		DHW_TIMER_FRIDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_FRIDAY_DEBUG)),
		
		DHW_TIMER_SATURDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0052
		DHW_TIMER_SATURDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(DHW_TIMER_SATURDAY_DEBUG)),
		
		CIRCUIT2_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x005A
		CIRCUIT2_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(CIRCUIT2_ENABLE_DEBUG)),
		
		CIRCUIT2_COOLING_SETPOINT_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x005B
		CIRCUIT2_COOLING_SETPOINT(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(CIRCUIT2_COOLING_SETPOINT_DEBUG)),
		
		CIRCUIT2_HEATINGCURVE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x005C
		CIRCUIT2_HEATINGCURVE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(CIRCUIT2_HEATINGCURVE_ENABLE_DEBUG)),
		
		CIRCUIT2_HEATING_SETPOINT_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0062
		CIRCUIT2_HEATING_SETPOINT(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(CIRCUIT2_HEATING_SETPOINT_DEBUG)),
		
		
		SLEEP_MODE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0065
		SLEEP_MODE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_ENABLE_DEBUG)),
		
		SLEEP_MODE_DELTA_T_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0066
		SLEEP_MODE_DELTA_T(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_DELTA_T_DEBUG)),
		
		SLEEP_MODE_TIMER_SUNDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0067
		SLEEP_MODE_TIMER_SUNDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_SUNDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_MONDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0068
		SLEEP_MODE_TIMER_MONDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_MONDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_TUESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0069
		SLEEP_MODE_TIMER_TUESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_TUESDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_WEDNESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006A
		SLEEP_MODE_TIMER_WEDNESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_WEDNESDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_THURSDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006B
		SLEEP_MODE_TIMER_THURSDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_THURSDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_FRIDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006C
		SLEEP_MODE_TIMER_FRIDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_FRIDAY_DEBUG)),
		
		SLEEP_MODE_TIMER_SATURDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006D
		SLEEP_MODE_TIMER_SATURDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(SLEEP_MODE_TIMER_SATURDAY_DEBUG)),
		
		
		LOW_NOISE_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006E
		LOW_NOISE_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_ENABLE_DEBUG)),
		
		LOW_NOISE_DELTA_T_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x006F
		LOW_NOISE_DELTA_T(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_DELTA_T_DEBUG)),
		
		LOW_NOISE_TIMER_SUNDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0070
		LOW_NOISE_TIMER_SUNDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_SUNDAY_DEBUG)),
		
		LOW_NOISE_TIMER_MONDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0071
		LOW_NOISE_TIMER_MONDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_MONDAY_DEBUG)),
		
		LOW_NOISE_TIMER_TUESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0072
		LOW_NOISE_TIMER_TUESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_TUESDAY_DEBUG)),
		
		LOW_NOISE_TIMER_WEDNESDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0073
		LOW_NOISE_TIMER_WEDNESDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_WEDNESDAY_DEBUG)),
		
		LOW_NOISE_TIMER_THURSDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0074
		LOW_NOISE_TIMER_THURSDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_THURSDAY_DEBUG)),
		
		LOW_NOISE_TIMER_FRIDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0075
		LOW_NOISE_TIMER_FRIDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_FRIDAY_DEBUG)),
		
		LOW_NOISE_TIMER_SATURDAY_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0076
		LOW_NOISE_TIMER_SATURDAY(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.NONE)
				.onChannelSetNextWriteMirrorToDebugChannel(LOW_NOISE_TIMER_SATURDAY_DEBUG)),
		
		AMBIENT_TO_START_DHW_ECO_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0085
		AMBIENT_TO_START_DHW_ECO(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TO_START_DHW_ECO_DEBUG)),
		
		AMBIENT_TO_START_HEATING_ECO_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0086
		AMBIENT_TO_START_HEATING_ECO(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.DEGREE_CELSIUS)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TO_START_HEATING_ECO_DEBUG)),
		
		AMBIENT_TO_START_DHW_ECO_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0087
		AMBIENT_TO_START_DHW_ECO_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TO_START_DHW_ECO_ENABLE_DEBUG)),
		
		AMBIENT_TO_START_HEATING_ECO_ENABLE_DEBUG(Doc.of(OpenemsType.INTEGER)), // 0x0088
		AMBIENT_TO_START_HEATING_ECO_ENABLE(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE)
				.unit(Unit.ON_OFF)
				.onChannelSetNextWriteMirrorToDebugChannel(AMBIENT_TO_START_HEATING_ECO_ENABLE_DEBUG)),
		
		
		// ==== Parameters for read only FC03 ====
		SOFTWARE_VERSION(Doc.of(OpenemsType.INTEGER) // 0x0001
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DATABASE_VERSION(Doc.of(OpenemsType.INTEGER) // 0x0002
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		LANGUAGE_SELECTION(Doc.of(OpenemsType.INTEGER) // 0x0004
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WATER_OR_ROOM_CONTROL(Doc.of(OpenemsType.INTEGER) // 0x0009
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		MAX_HEATING_SET_TEMPERATURE(Doc.of(OpenemsType.INTEGER) // 0x000D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		MIN_HEATING_SET_TEMPERATURE(Doc.of(OpenemsType.INTEGER) // 0x000E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		MAX_COOLING_SET_TEMPERATURE(Doc.of(OpenemsType.INTEGER) // 0x000F
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		MIN_COOLING_SET_TEMPERATURE(Doc.of(OpenemsType.INTEGER) // 0x0010
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		TIMER_ACTIVE(Doc.of(OpenemsType.INTEGER) // 0x0012 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		SUNDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0013 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		MONDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0014 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		TUESDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0015 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WEDNESDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0016 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		THURSDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0017 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		FRIDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0018 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		SATURDAY_TIMER(Doc.of(OpenemsType.INTEGER) // 0x0019 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		AMBIENT_TEMPERATURE1(Doc.of(OpenemsType.INTEGER) // 0x001F 
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		AMBIENT_TEMPERATURE2(Doc.of(OpenemsType.INTEGER) // 0x0020
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		AMBIENT_TEMPERATURE3(Doc.of(OpenemsType.INTEGER) // 0x0021
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		AMBIENT_TEMPERATURE4(Doc.of(OpenemsType.INTEGER) // 0x0022
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		AMBIENT_TEMPERATURE5(Doc.of(OpenemsType.INTEGER) // 0x0023
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT1(Doc.of(OpenemsType.INTEGER) // 0x0024
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT2(Doc.of(OpenemsType.INTEGER) // 0x0025
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT3(Doc.of(OpenemsType.INTEGER) // 0x0026
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT4(Doc.of(OpenemsType.INTEGER) // 0x0027
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT5(Doc.of(OpenemsType.INTEGER) // 0x0028
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		IDEAL_ROOM_TEMP_FOR_HEATING(Doc.of(OpenemsType.INTEGER) // 0x002A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		IDEAL_ROOM_TEMP_FOR_COOLING(Doc.of(OpenemsType.INTEGER) // 0x002B
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SYSTEM_1_MAX_SETPOINT(Doc.of(OpenemsType.INTEGER) // 0x002D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SYSTEM_1_MIN_SETPOINT(Doc.of(OpenemsType.INTEGER) // 0x002E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SETPOINT_ANTILEGIONELLA(Doc.of(OpenemsType.INTEGER) // 0x0031
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		DURATION_ANTILEGIONELLA(Doc.of(OpenemsType.INTEGER) // 0x0032
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		MAX_DURATION_ANTILEGIONELLA(Doc.of(OpenemsType.INTEGER) // 0x0033
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		DELTA_T_DHW_HOLIDAY_MODE(Doc.of(OpenemsType.INTEGER) // 0x0035
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		DELTA_T_HEATING_HOLIDAY_MODE(Doc.of(OpenemsType.INTEGER) // 0x0036
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HOLIDAY_MODE_START_DATE(Doc.of(OpenemsType.INTEGER) // 0x0037
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		HOLIDAY_MODE_END_DATE(Doc.of(OpenemsType.INTEGER) // 0x0038
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		HEATING_BACKUP_HEATER_STATUS(Doc.of(OpenemsType.INTEGER) // 0x0039
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		PRIORITY_AH_HBH(Doc.of(OpenemsType.INTEGER) // 0x003A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		HOT_WATER_BACKUP_HEATER_STATUS(Doc.of(OpenemsType.INTEGER) // 0x003B
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		PRIORITY_AH_HWTBH(Doc.of(OpenemsType.INTEGER) // 0x003C
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		SETTING_TO_ACTIVATE_HWTBH_IN_DHW_MODE(Doc.of(OpenemsType.INTEGER)  // 0x003D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		TIME_TO_ACTIVATE_HWTBH_IN_DHW_MODE(Doc.of(OpenemsType.INTEGER) // 0x003E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		DHW_REHEATING_SETPOINT(Doc.of(OpenemsType.INTEGER) // 0x004A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		DHW_REHEATING_RESTART_POINT(Doc.of(OpenemsType.INTEGER) // 0x004B
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		DHW_REHEATING_TIMER_SUNDAY(Doc.of(OpenemsType.INTEGER) // 0x0053
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_MONDAY(Doc.of(OpenemsType.INTEGER) // 0x0054
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_TUESDAY(Doc.of(OpenemsType.INTEGER) // 0x0055
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_WEDNESDAY(Doc.of(OpenemsType.INTEGER) // 0x0056
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_THURSDAY(Doc.of(OpenemsType.INTEGER) // 0x0057
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_FRIDAY(Doc.of(OpenemsType.INTEGER) // 0x0058
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DHW_REHEATING_TIMER_SATURDAY(Doc.of(OpenemsType.INTEGER) // 0x0059
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		CIRCUIT2_SETPOINT1(Doc.of(OpenemsType.INTEGER) // 0x005D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_SETPOINT2(Doc.of(OpenemsType.INTEGER) // 0x005E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_SETPOINT3(Doc.of(OpenemsType.INTEGER) // 0x005F
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_SETPOINT4(Doc.of(OpenemsType.INTEGER) // 0x0060
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_SETPOINT5(Doc.of(OpenemsType.INTEGER) // 0x0061
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_MAX_SETPOINT(Doc.of(OpenemsType.INTEGER) // 0x0063
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		CIRCUIT2_MIN_SETPOINT(Doc.of(OpenemsType.INTEGER) // 0x0064
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		BACKLIGHT(Doc.of(OpenemsType.INTEGER) // 0x007E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		BAUD_RATE(Doc.of(OpenemsType.INTEGER) // 0x007F
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WORD_LENGTH(Doc.of(OpenemsType.INTEGER) // 0x0080
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		PARITY(Doc.of(OpenemsType.INTEGER) // 0x0081
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		STOPBITS(Doc.of(OpenemsType.INTEGER) // 0x0082
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WATER_PUMP_TYPE(Doc.of(OpenemsType.INTEGER) // 0x0089
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WATER_PUMP_WORKING_MODE(Doc.of(OpenemsType.INTEGER) // 0x008B
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		PUMP_OFF_DURATION_INTERVAL_MODE(Doc.of(OpenemsType.INTEGER) // 0x008C
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		PUMP_ON_DURATION_INTERVAL_MODE(Doc.of(OpenemsType.INTEGER) // 0x008D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		BFT(Doc.of(OpenemsType.INTEGER) // 0x008E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		MIXINGVALVE1_STATUS(Doc.of(OpenemsType.INTEGER) // 0x008F
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		MIXINGVALVE2_STATUS(Doc.of(OpenemsType.INTEGER) // 0x0090
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		THREE_WAY_VALVE_SWITHING_TIME(Doc.of(OpenemsType.INTEGER) // 0x00AF
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		THREE_WAY_VALVE_POWERING_TIME(Doc.of(OpenemsType.INTEGER) // 0x00B0
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.MINUTE)),
		
		PUMP_SPEED_WHEN_HEATING(Doc.of(OpenemsType.INTEGER) // 0x00D6
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		PUMP_SPEED_WHEN_COOLING(Doc.of(OpenemsType.INTEGER) // 0x00D7
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		PUMP_SPEED_WHEN_DHW(Doc.of(OpenemsType.INTEGER) // 0x00D8
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		// ==== Running data, read only FC03 ====
		MODE_DHW(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.00
		MODE_HEATING(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.01
		MODE_COOLING(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.02
		MODE_DHW_IN_PROCESS(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.03
		MODE_HEATING_IN_PROCESS(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.04
		MODE_COOLING_IN_PROCESS(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.05
		MODE_TIMER_IN_PROCESS(Doc.of(OpenemsType.BOOLEAN)), // 0X1F3.06
		
		SYSTEM_LOGIN_STATUS(Doc.of(OpenemsType.INTEGER) // 0x01F4
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		SOFTWARE_VERSION_PARM(Doc.of(OpenemsType.INTEGER) // 0x01F5
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		DATABASE_VERSION_PARM(Doc.of(OpenemsType.INTEGER) // 0x01F6
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		EEPROM_VERSION(Doc.of(OpenemsType.INTEGER) // 0x01F7
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		OUTDOORUNIT_SOFTWARE_VERSION_PARM(Doc.of(OpenemsType.INTEGER) // 0x01F8
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		WATER_OUTLET_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01F9
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		WATER_INLET_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FA
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		INDOOR_COIL_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FB
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		DHW_WATER_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FC
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		SERVICE_WATER_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FD
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		MIXING_VALVE1_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FE
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		MIXING_VALVE2_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x01FF
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		OPERATING_STATUS(Doc.of(OpenemsType.INTEGER) // 0x0202
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		COMPRESSOR_SPEED(Doc.of(OpenemsType.INTEGER) // 0x0203
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		EEV_OPENING(Doc.of(OpenemsType.INTEGER) // 0x0204
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		AMBIENT_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x0205
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		HIGH_PRESSURE(Doc.of(OpenemsType.FLOAT) // 0x0209
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		LOW_PRESSURE(Doc.of(OpenemsType.FLOAT) // 0x020A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		COMPRESSOR_DISCHARGE_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x020B
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		COMPRESSOR_SUCTION_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x020C
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		OUTDOOR_COIL_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x020D
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		FAN1_RPM(Doc.of(OpenemsType.INTEGER) // 0x020E
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		FAN2_RPM(Doc.of(OpenemsType.INTEGER) // 0x020F
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		RUNNING_CURRENT(Doc.of(OpenemsType.FLOAT) // 0x0210
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.AMPERE)),
		
		SUPPLY_VOLTAGE(Doc.of(OpenemsType.INTEGER) // 0x0211
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.VOLT)),
		
		DEFROST_STATUS(Doc.of(OpenemsType.INTEGER) // 0x0212
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		ROOM_TEMPERATURE(Doc.of(OpenemsType.FLOAT) // 0x0213
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.DEGREE_CELSIUS)),
		
		WATER_FLOW_SWITCH(Doc.of(OpenemsType.INTEGER) // 0x0214
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		UTILITY_LOCK(Doc.of(OpenemsType.INTEGER) // 0x0215
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		COOLING_EXTERNAL(Doc.of(OpenemsType.INTEGER) // 0x0216
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		HEATING_EXTERNAL(Doc.of(OpenemsType.INTEGER) // 0x0217
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		HIGH_DEMAND_REQUEST(Doc.of(OpenemsType.INTEGER) // 0x0218
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.ON_OFF)),
		
		PWM_SIGNAL(Doc.of(OpenemsType.FLOAT) // 0x0219
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.NONE)),
		
		MIXING_VALVE1_COMMAND(Doc.of(OpenemsType.FLOAT) // 0x021A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.VOLT)),
		
		MIXING_VALVE2_COMMAND(Doc.of(OpenemsType.FLOAT) // 0x021A
				.accessMode(AccessMode.READ_ONLY)
				.unit(Unit.VOLT)),

		FAILURE1_MAINS_CURRENT(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21C.00
		FAILURE1_COMPRESSOR_CURRENT(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21C.01
		FAILURE1_IPM_MODULE(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21C.02
		FAILURE1_COMPRESSOR_OIL_RETURN(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.03
		FAILURE1_HIGH_PRESSURE_ON_SWITCH(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21C.04
		FAILURE1_COMPRESSOR_OVERPRESSURE_SHUTDOWN(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.05
		FAILURE1_FIRST_START_PREHEAT(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.06
		FAILURE1_OUTDORR_GAS_DISCHARGE_TEMP(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.07
		FAILURE1_OUTDOOR_COIL_EVAPORATOR_TEMP(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.08
		FAILURE1_AC_HIGH_LOW_VOLTAG(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21C.09
		FAILURE1_AMBIENT_TEMPERATURE(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21C.10
		FAILURE1_FREQUENCY_LIMIT_BY_AMBIENT(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.11
		FAILURE1_LOW_PRESSURE_SWITCH(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.12
		FAILURE1_COMM_LOST_PANEL(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.13
		FAILURE1_COMM_LOST_COMPRESSOR(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.14
		FAILURE1_COMPRESSOR_PHASE_OPEN_SHORT(Doc.of(OpenemsType.BOOLEAN)), // 0X21C.15
		
		FAILURE2_COMPRESSOR_WORKING_CURRENT(Doc.of(OpenemsType.BOOLEAN)),// 0X21D.00
		FAILURE2_COMPRESSOR_DRIVER(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21D.01
		FAILURE2_COMPRESSOR_DRIVER_VDC(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21D.02
		FAILURE2_AC_CURRENT(Doc.of(OpenemsType.BOOLEAN)), 				// 0X21D.03
		FAILURE2_EEPROM(Doc.of(OpenemsType.BOOLEAN)), 					// 0X21D.04
		FAILURE2_AMBIENT_TEMPERATURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)),// 0X21D.05
		FAILURE2_OUTDOOR_COIL_TEMPERATURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), // 0X21D.06
		FAILURE2_COMPRESSOR_DISCHARGE_TEMPERATURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), // 0X21D.07
		FAILURE2_COMPRESSOR_SUCTION_TEMPERATURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), // 0X21D.08
		FAILURE2_LOW_PRESSURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21D.09
		FAILURE2_HIGH_PRESSURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21D.10
		FAILURE2_HIGH_PRESSURE_SWITCH(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21D.11
		FAILURE2_FLOW_SWITCH(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21D.12
		FAILURE2_DC_FAN_MOTOR_A(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21D.13
		FAILURE2_DC_FAN_MOTOR_B(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21D.14
		FAILURE2_EVAPORATING_PRESSURE(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21D.15
		
		FAILURE3_HIGH_PRESSURE(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21E.00
		FAILURE3_ROOM_TEMPERATURE_SENSOR(Doc.of(OpenemsType.BOOLEAN)), // 0X21E.01
		FAILURE3_TW_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21E.02
		FAILURE3_TC_sENSOR(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21E.03
		FAILURE3_WATER_OUTLET(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21E.04
		FAILURE3_WATER_INLET(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21E.05
		FAILURE3_INDOOR_COIL_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21E.06
		FAILURE3_VALVE1_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21E.07
		FAILURE3_VALVE2_SENSOR(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21E.08
		FAILURE3_COMM_LOST_PANEL_2(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21E.09
		FAILURE3_INDOOR_EEPROM(Doc.of(OpenemsType.BOOLEAN)), 		// 0X21E.10
		FAILURE3_PWM_SIGNAL(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21E.11
		FAILURE3_VALVE1(Doc.of(OpenemsType.BOOLEAN)), 				// 0X21E.12
		FAILURE3_VALVE2(Doc.of(OpenemsType.BOOLEAN)), 				// 0X21E.13
		FAILURE3_INDOOR_ANTIFREEZE(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21E.14
		FAILURE3_FLOW_SWITCH_PROTECTION(Doc.of(OpenemsType.BOOLEAN)),// 0X21E.15
		
		FAILURE4_FLOW_SWITCH2(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21F.00
		FAILURE4_INDOOR_COMM(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21F.01
		FAILURE4_OUTDOOR_COMM(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21F.02
		FAILURE4_LOW_OUTLET_TEMP_COOLING(Doc.of(OpenemsType.BOOLEAN)),// 0X21F.03
		FAILURE4_HIGH_OUTLET_TEMP_IN_HEATING(Doc.of(OpenemsType.BOOLEAN)),// 0X21F.04
		FAILURE4_DEFROST_FAILURE_REPEATED(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21F.05
		FAILURE4_LOW_OUTLET_IN_HEATING(Doc.of(OpenemsType.BOOLEAN)), // 0X21F.06
		FAILURE4_FLOW_SWITCH_REPEATED(Doc.of(OpenemsType.BOOLEAN)),	// 0X21F.07
		FAILURE4_COIL_TEMP_PROTECTION(Doc.of(OpenemsType.BOOLEAN)), 	// 0X21F.08
		FAILURE4_PREHEATING(Doc.of(OpenemsType.BOOLEAN)), 			// 0X21F.09
		

		
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
