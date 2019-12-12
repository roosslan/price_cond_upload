package com.rasa;

import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SapConfig {
    private String serviceRoot;
    private String recUrl;
    private String username;
    private String password;
    private String entity;
    private String rec_base;

    public static SapConfig load() {
        try (InputStream in = new FileInputStream(new File("config.toml"))) {
            return new Toml().read(in).getTable("sap").to(SapConfig.class);
        } catch (Exception e) {
            try (InputStream in = SapConfig.class.getClassLoader().getResourceAsStream("config.toml")) {
                if (in != null) {
                    return new Toml().read(in).getTable("sap").to(SapConfig.class);
                }
            } catch (Exception ignored) {}
            throw new RuntimeException("Failed to load config.toml", e);
        }
    }

    public String getServiceRoot() {
        return serviceRoot;
    }

    public String getRecUrl() {
        return recUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEntity() {
        return entity;
    }

    public String get_base_url() {
        return rec_base;
    }

    public String getRec_base() {
        return rec_base;
    }
}
