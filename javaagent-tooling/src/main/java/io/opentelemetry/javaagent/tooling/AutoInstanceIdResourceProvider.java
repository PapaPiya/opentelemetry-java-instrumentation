package io.opentelemetry.javaagent.tooling;

import com.google.auto.service.AutoService;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.autoconfigure.spi.ConfigProperties;
import io.opentelemetry.sdk.autoconfigure.spi.ResourceProvider;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.semconv.resource.attributes.ResourceAttributes;
import java.util.Map;
import java.util.UUID;

@AutoService(ResourceProvider.class)
public class AutoInstanceIdResourceProvider implements ResourceProvider {

  @Override
  public Resource createResource(ConfigProperties config) {
    Map<String, String> resourceAttributes = config.getMap("otel.resource.attributes");
    return resourceAttributes.containsKey(ResourceAttributes.SERVICE_INSTANCE_ID.getKey())
        ? Resource.create(Attributes.of(ResourceAttributes.SERVICE_INSTANCE_ID, resourceAttributes.get(ResourceAttributes.SERVICE_INSTANCE_ID.getKey())))
        : Resource.create(Attributes.of(ResourceAttributes.SERVICE_INSTANCE_ID, UUID.randomUUID().toString()));
  }
}
