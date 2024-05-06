package io.openems.edge.bridge.modbus.api.element;

import java.nio.ByteBuffer;

import io.openems.common.types.OpenemsType;
import io.openems.edge.common.type.TypeUtils;

/**
 * An UnsignedWordElement represents an Integer value in an
 * {@link AbstractSingleWordElement}.
 */
public class UnsignedWordElementFloat extends AbstractSingleWordElement<UnsignedWordElementFloat, Float> {

	public UnsignedWordElementFloat(int address) {
		super(OpenemsType.FLOAT, address);
	}

	@Override
	protected UnsignedWordElementFloat self() {
		return this;
	}

	@Override
	protected Float byteBufferToValue(ByteBuffer buff) {
		return buff.getFloat(0);
	}

	@Override
	protected void valueToByteBuffer(ByteBuffer buff, Float value) {
		Integer i = TypeUtils.getAsType(OpenemsType.FLOAT, value);
		buff.putShort(i.shortValue());
	}

}