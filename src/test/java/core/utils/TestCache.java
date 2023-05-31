package core.utils;

import core.enums.TestCacheKey;
import org.assertj.core.api.Assertions;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class TestCache {

  private static final ThreadLocal<TestSessionMap> CACHE = new InheritableThreadLocal<>();


  public static void initializeTestCache() {
    CACHE.set(new TestSessionMap());
  }

  public static void putDataInCache(final TestCacheKey key, final Object value) {
    getCache().put(key, value);
  }

  private static TestSessionMap getCache() {
    return CACHE.get();
  }

  public static <T> T get(final TestCacheKey key, final Class<?> type) {
    Object value = getCache().get(key);
    if (Objects.nonNull(value)) {
      Assertions.assertThat(value).as("Incompatible type of returned from TestCache object")
        .isInstanceOf(type);
    }
    return Objects.nonNull(value) ? (T) value : null;
  }

  private static class TestSessionMap extends ConcurrentHashMap<Object, Object> {

    @Override
    public Object put(final Object key, final Object value) {
      return Objects.isNull(value) ? super.remove(key) : super.put(key, value);
    }
  }
}
