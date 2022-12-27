# Dozer

## What is it?

Dozer is a modified client for the [Minecraft](http://minecraft.net) game, version beta 1.7.3. It is a fork of
the [Minecraft Coder Pack](http://www.minecraftforum.net/topic/97940-), which is a set of tools for modding the game.
Dozer is a fork of MCP, and is intended to be a utility mod for the `2beta2t.net` minecraft server.

## How do I use it?

Dozer's default key bind for it's ClickGUI is `RSHIFT` / `RIGHT SHIFT`.
You can change this by right-clicking the module you wish to change the key bind of.

## How to add an event

### Creating the event class

1. Create a new Class in the `dozer.events.impl` package.
2. Create a new method in the class, with the following signature: `public void EventName()` with any parameters you'd
   need.

### Registering the event

1. Head to the class and method you want to register the event to e.g `net.minecraft.src.entity.EntityPlayer.java:54` (For the
   onUpdate event
2. Add the following line of code: `Dozer.getSingleton().getEventBus().post(new EventName());`

### Creating the event class

#### Example:

```java
package dozer.events.impl;

public class EventName {
    
    public int someInt; // Some int
    
    /**
     * Constructor for the event
     * @param someInt Some int
     */
    public EventName(int someInt) {
        this.someInt = someInt;
    }
    
    /**
     * @return the someInt
     */
    public int getSomeInt() {
        return someInt;
    }
    
}
```

### Registering the event

#### Example:

```java
package net.minecraft.src;

import net.minecraft.src.entity.EntityLiving;

public class EntityPlayer extends EntityLiving {

  public void onUpdate() {
    Dozer.getSingleton().getEventBus().post(new EventName(1));
  }

}
```

or

```java
package net.minecraft.src;

import net.minecraft.src.entity.EntityLiving;

public class EntityPlayer extends EntityLiving {

  public void onUpdate() {
    EventName event = new EventName(1);
    Dozer.getSingleton().getEventBus().post(event);
  }

}
```

## How to add a module

### Creating the module class

1. Create a new Class in the `dozer.modules.impl.categoryName` package.
2. At the top of the class, add the following line of
   code: `@ModuleInfo(name = "ModuleName", description = "ModuleDescription", category = ModuleCategory.CategoryName)`

#### Example:

```java
package dozer.modules.impl.categoryName;

import dozer.modules.Module;

@ModuleInfo(name = "ModuleName", description = "ModuleDescription", category = ModuleCategory.CategoryName)
public class ModuleName extends Module {
    
    // Module code
    
}
```

### Calling an Event in a module

#### Example:

```java
package dozer.modules.impl.categoryName;

import dozer.event.Subscribe;
import dozer.events.impl.EventName;

@ModuleInfo(name = "ModuleName", description = "ModuleDescription", category = ModuleCategory.CategoryName)
public class ModuleName extends Module {

    @Subscribe
    public void onEventName(EventName event) {
        // Module code
    }

}
```