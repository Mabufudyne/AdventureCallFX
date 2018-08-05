package org.mabufudyne.designer.core;

import java.io.*;
import java.util.logging.Logger;

public class FileHandler {

    private final static Logger LOGGER = Logger.getLogger(FileHandler.class.getName());

    private FileHandler() {}

    public static String saveAdventure(Adventure adv, String location, String fileName) {

        fileName = fileName.endsWith(".adv") ? fileName : fileName + ".adv";

        try (
                FileOutputStream fileOut = new FileOutputStream(location + File.separator + fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            out.writeObject(adv);
            return location + File.separator + fileName;
        }
        catch (IOException e) {
            LOGGER.severe(String.format("Encountered an error while saving the Adventure to '%s': '%s'",
                    location, e.getMessage()));
            e.printStackTrace();

            return null;
        }
    }

    public static Adventure loadAdventure(String filePath) {
        Adventure loadedAdv = null;

        try (
                FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInput in = new ObjectInputStream(fileIn)
        )
        {
            loadedAdv = (Adventure) in.readObject();
            Adventure.setActiveAdventure(loadedAdv);
        } catch (IOException e) {
            LOGGER.severe("Error while trying to load the file: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException | ClassCastException e) {
            LOGGER.severe(String.format("The given file '%s' cannot be loaded as an Adventure object", filePath));
            e.printStackTrace();
        }

        return loadedAdv;
    }

}
