package ecp.ems.edge.modbusgateway;

import org.junit.Test;

import io.openems.common.types.ChannelAddress;
import io.openems.edge.bridge.modbus.test.DummyModbusBridge;
import io.openems.edge.common.test.AbstractComponentTest.TestCase;
import io.openems.edge.common.test.ComponentTest;
import io.openems.edge.common.test.DummyConfigurationAdmin;

public class ModbusGatewayImplTest {

	private static final String COMPONENT_ID = "chp0";
	private static final String MODBUS_ID = "modbus0";
	
	private static final ChannelAddress VPP_MODE = new ChannelAddress(COMPONENT_ID, ModbusGateway.ChannelId.VPP_MODE.id());
	private static final ChannelAddress CHP_LOAD_LEVEL = new ChannelAddress(COMPONENT_ID, ModbusGateway.ChannelId.CHP_LOAD_LEVEL.id());

	@Test
	public void test() throws Exception {
		new ComponentTest(new ModbusGatewayImpl()) //
				.addReference("cm", new DummyConfigurationAdmin()) //
				.addReference("setModbus", new DummyModbusBridge(MODBUS_ID)) //
				.activate(MyConfig.create() //
						.setId(COMPONENT_ID) //
						.setModbusId(MODBUS_ID) //
						.setModbusUnitId(2) //
						.build())
				.next(new TestCase()
						.inputForce(VPP_MODE, 1)
						.inputForce(CHP_LOAD_LEVEL,100));
	}

}