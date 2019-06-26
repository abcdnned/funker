package service;

import java.io.IOException;

public interface EnergyService {

    public void initEnergy() throws IOException;

    public int getEnergy() throws IOException;

    public void addEnergy (final int var) throws IOException;

    public void reduceEnergy (final int var) throws IOException;

}
