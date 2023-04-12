package net.internalerror.betterfactories.forge.energy;

import net.minecraftforge.energy.EnergyStorage;

/**
 * {@link net.internalerror.betterfactories.BetterFactories}
 *
 * @author InternalErrorGit
 * @version 1.0 Snapshot
 */
public abstract class BFEnergyStorage extends EnergyStorage {
  
  /**
   * @param pCapacity
   *     The capacity
   * @param pMaxTransfer
   *     The max transfer
   */
  protected BFEnergyStorage(int pCapacity, int pMaxTransfer) {
    super(pCapacity, pMaxTransfer);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int receiveEnergy(int pMaxReceive, boolean pSimulate) {
    int receiveEnergy = super.receiveEnergy(pMaxReceive, pSimulate);
    if (receiveEnergy != 0) {
      onEnergyChanged();
    }
    return receiveEnergy;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int extractEnergy(int pMaxExtract, boolean pSimulate) {
    int extractEnergy = super.extractEnergy(pMaxExtract, pSimulate);
    if (extractEnergy != 0) {
      onEnergyChanged();
    }
    return extractEnergy;
  }
  
  /**
   * @param pCapacity
   *     The new capacity
   */
  public void setCapacity(int pCapacity) {
    capacity = pCapacity;
  }
  
  public abstract void onEnergyChanged();
  
  /**
   * @param pEnergy
   *     The new energy level
   */
  public void setEnergy(int pEnergy) {
    energy = pEnergy;
    onEnergyChanged();
  }
  
}
