package io.opentelemetry.sdk.extension.trace.jaeger.sampler;

import io.opentelemetry.exporter.internal.marshal.MarshalerUtil;
import io.opentelemetry.exporter.internal.marshal.MarshalerWithSize;
import io.opentelemetry.exporter.internal.marshal.ProtoFieldInfo;
import io.opentelemetry.exporter.internal.marshal.Serializer;
import java.io.IOException;

final class SamplingStrategyParametersMarshaler extends MarshalerWithSize {

  private final byte[] serviceNameUtf8;
  public static final ProtoFieldInfo SERVICENAME = ProtoFieldInfo.create(1, 10, "serviceName");

  static SamplingStrategyParametersMarshaler create(String serviceName) {
    return new SamplingStrategyParametersMarshaler(MarshalerUtil.toBytes(serviceName));
  }

  private SamplingStrategyParametersMarshaler(byte[] serviceName) {
    super(calculateSize(serviceName));
    this.serviceNameUtf8 = serviceName;
  }

  @Override
  protected void writeTo(Serializer output) throws IOException {
    output.serializeString(SERVICENAME, serviceNameUtf8);
  }

  private static int calculateSize(byte[] serviceName) {
    return MarshalerUtil.sizeBytes(SERVICENAME, serviceName);
  }
}
