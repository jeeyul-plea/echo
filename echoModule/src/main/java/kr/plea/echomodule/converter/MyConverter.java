package kr.plea.echomodule.converter;

import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import kr.plea.echomodule.custom.DecryptId;
import kr.plea.echomodule.encrypter.MemberIdEncryptor;

public class MyConverter implements ConditionalGenericConverter {
	@Override
	public boolean matches(final TypeDescriptor sourceType, final TypeDescriptor targetType) {
		return targetType.hasAnnotation(DecryptId.class);
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Set.of(
			new ConvertiblePair(String.class, Long.class)
		);
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return MemberIdEncryptor.decrypt((String) source);
	}
}
