package com.rasa;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class fxHKontroller {
    public TextField dateiPfad;

    public void onHochladenBtnClick(ActionEvent actionEvent) throws IOException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SapConfig config = SapConfig.load();

        String sapServiceRoot = config.getServiceRoot();
        String basicAuth = "Basic " + Base64.encodeBase64String(
                String.format("%s:%s", config.getUsername(), config.getPassword())
                        .getBytes(StandardCharsets.UTF_8)
        );
        CloseableHttpClient httpClient = HttpClients.custom().build();

        HttpPost request = new HttpPost(sapServiceRoot + "/" + config.getEntity());
        String base = config.get_base_url();
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><entry xml:base=\"" + base + config.getRecUrl() + "/\" xmlns=\"http://www.w3.org/2005/Atom\" xmlns:m=\"http://schemas.microsoft.com/ado/2007/08/dataservices/metadata\" xmlns:d=\"http://schemas.microsoft.com/ado/2007/08/dataservices\"><id>" + base + "sap/" + config.getRecUrl() + "/" + config.getEntity() + "(1)</id>\n" +
                "<title type=\"text\">" + config.getEntity() + "(1)</title><updated>2000-01-01T01:01:01Z</updated><category term=\"" + config.getRecUrl() + ".infosatz\" scheme=\"http://schemas.microsoft.com/ado/2007/08/dataservices/scheme\"/><link href=\"" + config.getEntity() + "(1)\" rel=\"self\" title=\"infosatz\"/><content type=\"application/xml\"><m:properties>  <d:Index>43</d:Index>  <d:Kschl>unas</d:Kschl></m:properties></content></entry>";
        HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
        request.setHeader("Content-type", "application/atom+xml");
        request.setHeader("X-Requested-With", "X");
        request.setHeader("Authorization", basicAuth);
        request.setEntity(entity);
        HttpResponse antwort = httpClient.execute(request);
        String ergebnis = EntityUtils.toString(antwort.getEntity());
        System.out.println(ergebnis);
    }

    public void onOeffnenBtnClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(0));
        File file = fileChooser.showOpenDialog(fxLoginController.secondStage);
        if (file != null) {
            dateiPfad.setText(file.toString());
        }
    }
}
