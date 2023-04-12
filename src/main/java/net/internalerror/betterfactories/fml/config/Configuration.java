package net.internalerror.betterfactories.fml.config;

import lombok.experimental.UtilityClass;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
@UtilityClass
public final class Configuration {
  
  public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
  public static final ForgeConfigSpec SPEC;
  
  public static final ForgeConfigSpec.ConfigValue<Integer> CRUSHING_MACHINE_BASE_ENERGY_CAPACITY;
  public static final ForgeConfigSpec.ConfigValue<Integer> CRUSHING_MACHINE_BASE_ENERGY_TRANSFER;
  
  
  static {
    BUILDER.push("BetterFactories");
    BUILDER.push("Machines");
    BUILDER.push("CrushingMachine");
    CRUSHING_MACHINE_BASE_ENERGY_CAPACITY = minMaxConfig("Base energy capacity of the crushing machine", "CRUSHING_MACHINE_BASE_ENERGY_CAPACITY", 16384, 32768, 65536);
    CRUSHING_MACHINE_BASE_ENERGY_TRANSFER = minMaxConfig("Base energy transfer of the crushing machine", "CRUSHING_MACHINE_BASE_ENERGY_TRANSFER", 128, 512, 2048);
    BUILDER.pop();
    BUILDER.pop();
    BUILDER.pop();
    SPEC = BUILDER.build();
  }
  
  private static <T extends Number> ForgeConfigSpec.ConfigValue<T> minMaxConfig(String pComment, String pPath, T pMin, T pDefault, T pMax) {
    return BUILDER.comment(pComment, String.format("Min: %s; Default: %s; Max: %s;", pMin, pDefault, pMax))
        .define(pPath, pDefault, val -> {
          if (val instanceof Number number) {
            return pMin.floatValue() < number.floatValue() && pMax.floatValue() > number.floatValue();
          }
          return false;
        });
  }
  
  
}
