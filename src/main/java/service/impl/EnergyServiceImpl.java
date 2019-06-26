package service.impl;

import service.EnergyService;
import service.AppContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.springframework.stereotype.Service;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileOutputStream;

@Service
public class EnergyServiceImpl implements EnergyService {

    private final Logger LOG = LoggerFactory.getLogger(EnergyServiceImpl.class);

    @Override
    public void initEnergy() throws IOException {
        writeEnergy(0);
    }

    @Override
    public int getEnergy() throws IOException {
        final String path = AppContext.getEnvironments().getEnergyPath();
        final File f = new File(path);
        try (final DataInputStream inputStream = new DataInputStream(new FileInputStream(f))) {
            return inputStream.readInt();
        } catch (Throwable error) {
            LOG.error("get energy fail due to {}", error);
            throw error;
        }
    }

    @Override
    public void addEnergy(final int var) throws IOException {
        final int energy = getEnergy();
        writeEnergy(energy + var);
    }

    private void writeEnergy(final int var) throws IOException {
        final String path = AppContext.getEnvironments().getEnergyPath();
        final File f = new File(path);
        try (final DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(f))) {
            outputStream.writeInt(var);
        } catch (Throwable error) {
            LOG.error("write energy fail due to {}", error);
            throw error;
        }
    }

    @Override
    public void reduceEnergy(final int var) throws IOException {
        final int energy = getEnergy();
        writeEnergy(energy - var);
    }

}
