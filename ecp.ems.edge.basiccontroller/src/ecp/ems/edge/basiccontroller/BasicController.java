package ecp.ems.edge.basiccontroller;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.component.OpenemsComponent;
import io.openems.edge.controller.api.Controller;

public interface BasicController extends Controller, OpenemsComponent {

	public enum ChannelId implements io.openems.edge.common.channel.ChannelId {
		MODE(Doc.of(Mode.values()) //
				.initialValue(Mode.AUTOMATIC) //
				.text("Configured Mode")), //
		
		VPP_MODE_RUN(Doc.of(OpenemsType.INTEGER)
				.accessMode(AccessMode.READ_WRITE) //
				.unit(Unit.NONE)),//

		VPP_MODE_STOP(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_WRITE)// 
				.unit(Unit.NONE)),
		
		LOAD_LEVEL_RUN(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_WRITE)//
				.unit(Unit.PERCENT)),//
		
		LOAD_LEVEL_STOP(Doc.of(OpenemsType.INTEGER)// 
				.accessMode(AccessMode.READ_WRITE)// 
				.unit(Unit.PERCENT)),
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
