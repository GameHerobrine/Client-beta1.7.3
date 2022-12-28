package dozer.setting;

import dozer.module.Module;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Setting<T> {

    private T value;
    protected String name;
    protected Module parentModule;
    protected dependency dependency;

    public Setting(String name, dependency dependency, T value) {
        this.name = name;
        this.dependency = dependency;
        this.value = value;
    }

    public boolean isParentActive() {
        if (this.dependency == null) {
            return true;
        }
        return this.dependency.check();
    }

    @FunctionalInterface
    public interface dependency {
        boolean check();
    }

}
