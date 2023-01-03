package dozer.systems.setting;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

@Getter
public abstract class Setting<A, V> {

  private final String name;
  private final String description;
  private final Field field;
  private final A annotation;
  private final Object object;
  @Setter private Setting<?, ?> parent;
  @Setter private String parentMode;
  @Setter private String group;

  public Setting(Field field, Object object) {
    this.field = field;
    this.name = field.getAnnotation(Serialize.class).name();
    this.description = field.getAnnotation(Serialize.class).desc();
    this.annotation = (A) field.getAnnotations()[1];
    this.object = object;
  }

  @SneakyThrows
  protected void setFieldValue(V value) {
    this.field.set(this.object, value);
  }

  @SneakyThrows
  protected V getFieldValue() {
    return (V) this.field.get(this.object);
  }

  public abstract V getValue();
  public abstract void setValue(V t);
  public abstract void loadSetting(String s);

}
