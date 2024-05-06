package ecp.ems.edge.basiccontroller;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(//
		name = "EC Power Basic controller", //
		description = "Basic controller to activate CHP")
@interface Config {

	@AttributeDefinition(name = "Component-ID", description = "Unique ID of this Component")
	String id() default "ChpBasicCtrl0";

	@AttributeDefinition(name = "Alias", description = "Human-readable name of this Component; defaults to Component-ID")
	String alias() default "";

	@AttributeDefinition(name = "Is enabled?", description = "Is this Component enabled?")
	boolean enabled() default true;
	
	@AttributeDefinition(name = "Mode", description = "Set the type of mode.")
	Mode mode() default Mode.AUTOMATIC;
	
	@AttributeDefinition(name = "VPP Mode Channel", description = "Channel address of the VPP Mode channel")
	String vppModeChannelAddress();
	
	@AttributeDefinition(name = "Load Level Channel", description = "Channel address of the Load Level channel")
	String loadLevelChannelAddress();
	
	String webconsole_configurationFactory_nameHint() default "Controller ecp.ems.edge.basiccontroller [{id}]";

}