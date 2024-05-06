package io.openems.edge.bridge.modbus.api.element;

import java.nio.ByteBuffer;

import io.openems.common.types.OpenemsType;
import io.openems.edge.common.type.TypeUtils;

/**
 * An SignedWordElement interpreted as a Float by scaling using an Integer value in an
 * {@link AbstractSingleWordElement}.
 */
public class SignedWordElementFloat extends AbstractSingleWordElement<SignedWordElementFloat, Float> {
	
	public SignedWordElementFloat(int address) {
		super(OpenemsType.FLOAT, address);
	}

	@Override
	protected SignedWordElementFloat self() {
		return this;
	}
	
	@Override
	protected Float byteBufferToValue(ByteBuffer buff) {
		return buff.getFloat(0);
	}

	@Override
	protected void valueToByteBuffer(ByteBuffer buff, Float value) {
		Short s = TypeUtils.getAsType(OpenemsType.FLOAT, value);
		buff.putShort(s.shortValue());
	}
	
}
