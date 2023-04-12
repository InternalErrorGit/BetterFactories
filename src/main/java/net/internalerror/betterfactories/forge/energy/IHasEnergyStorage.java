package net.internalerror.betterfactories.forge.energy;

public interface IHasEnergyStorage {
  default void setEnergy(int pEnergy) {
    getEnergyStorage().setEnergy(pEnergy);
  }
  
  BFEnergyStorage getEnergyStorage();
  
  default int getEnergyStored(){
    return getEnergyStorage().getEnergyStored();
  }
  
  int getEnergyCapacity();
  
  int getMaxTransfer();
  
  BFEnergyStorage createEnergyStorage();
}
