package ecp.ems.edge.modbusgateway;

import io.openems.common.channel.AccessMode;
import io.openems.common.channel.Unit;
import io.openems.common.types.OpenemsType;
import io.openems.edge.common.channel.Doc;
import io.openems.edge.common.channel.IntegerDoc;
import io.openems.edge.common.component.OpenemsComponent;

public interface ModbusGateway extends OpenemsComponent {

	public static enum ChannelId implements io.openems.edge.common.channel.ChannelId {

		VPP_MODE_DEBUG(Doc.of(OpenemsType.INTEGER)),//
		VPP_MODE(new IntegerDoc() //
				.accessMode(AccessMode.READ_WRITE) //
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.VPP_MODE_DEBUG)),

		CHP_LOAD_LEVEL_DEBUG(Doc.of(OpenemsType.INTEGER)),//
		CHP_LOAD_LEVEL(new IntegerDoc()//
				.accessMode(AccessMode.READ_WRITE)//
				.unit(Unit.PERCENT)//
				.onChannelSetNextWriteMirrorToDebugChannel(ChannelId.CHP_LOAD_LEVEL_DEBUG)),


		ERROR_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.accessMode(AccessMode.READ_ONLY)),//

		RUNNING_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.accessMode(AccessMode.READ_ONLY)),//

		READY_ACTIVE(Doc.of(OpenemsType.BOOLEAN)//
				.accessMode(AccessMode.READ_ONLY)),//

		STORAGE_SEQUENCE_FOUND(Doc.of(OpenemsType.BOOLEAN)//
				.accessMode(AccessMode.READ_ONLY)),//

		SYSTEM_STATUS(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)),//

		ERROR_CODE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)),//

		POWER_ELECTRICAL(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.KILOWATT)),//

		POWER_THERMAL(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.KILOWATT)),//

		HOURS_TO_SERVICE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HOUR)),//

		SUM_RUNNING_HOURS(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HOUR)),//

		COOLING_CAPACITY(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.PERCENT)),//

		STORAGE_TOP_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		STORAGE_BOTTOM_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		SEPARATION_LAYER_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		FLOW_FORWARD_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		FLOW_RETURN_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		CHP_TO_NET_TEMPERATURE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.DEGREE_CELSIUS)),//

		L1L2_VOLTAGE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		L2L3_VOLTAGE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		L3L1_VOLTAGE(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.VOLT)),//

		GRID_FREQUENCY(Doc.of(OpenemsType.INTEGER)//
				.accessMode(AccessMode.READ_ONLY)//
				.unit(Unit.HERTZ)),//

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
