package ecp.ems.edge.modbusgateway;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(//
		name = "EC Power XRGI Modbusgateway interface", //
		description = "Implements a EC Power CHP control")
@interface Config {

	@AttributeDefinition(name = "Component-ID", description = "Unique ID of this Component")
	String id() default "chp0";

	@AttributeDefinition(name = "Alias", description = "Human-readable name of this Component; defaults to Component-ID")
	String alias() default "";

	@AttributeDefinition(name = "Is enabled?", description = "Is this Component enabled?")
	boolean enabled() default true;

	//@AttributeDefinition(name = "Start/stop behaviour?", description = "Should this Component be forced to start or stop?")
	//StartStopConfig startStop() default StartStopConfig.AUTO;

	@AttributeDefinition(name = "Modbus-ID", description = "ID of Modbus bridge.")
	String modbus_id() default "modbus0";

	@AttributeDefinition(name = "Modbus Unit-ID", description = "The Unit-ID of the Modbus device.")
	int modbusUnitId() default 2;

	@AttributeDefinition(name = "Modbus target filter", description = "This is auto-generated by 'Modbus-ID'.")
	String Modbus_target() default "(enabled=true)";
	
	@AttributeDefinition(name = "Modbus read delay", description = "Time between read of slow signals [s].")
	int modbusSlowReadDelay() default 10;

	String webconsole_configurationFactory_nameHint() default "EC Power Modbus Gateway [{id}]";

}