/*
 * Copyright (C) Gleidson Neves da Silveira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.gleidson28;

import io.github.gleidson28.decorator.GNDecorator;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/08/2021
 */
public enum App {

    INSTANCE;

    private final Properties properties = new Properties();
    private final GNDecorator decorator = new GNDecorator();

    private Locale locale = null;

    private final DoubleProperty width = new SimpleDoubleProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();

    App() {
        width.set( Double.parseDouble(getString("app.width")));
        height.set( Double.parseDouble(getString("app.height")));

        decorator.setMinWidth(Double.parseDouble(getString("app.min.width")));
        decorator.setMinHeight(Double.parseDouble(getString("app.min.height")));
    }

    public void setContent(Parent content) {
        decorator.setContent(content);
    }

    public GNDecorator getDecorator() {
        return this.decorator;
    }

    public void show(Parent root) {

        width.bind(decorator.widthProperty());
        height.bind(decorator.heightProperty());

        decorator.setWidth(Double.parseDouble(getString("app.width")));
        decorator.setHeight(Double.parseDouble(getString("app.height")));

        decorator.addStylesheets(
                getClass().getResource("/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/theme/css/material-color.css").toExternalForm(),
                getClass().getResource("/theme/css/skeleton.css").toExternalForm(),
                getClass().getResource("/theme/css/light.css").toExternalForm(),
                getClass().getResource("/theme/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/theme/css/simple-info.css").toExternalForm(),
                getClass().getResource("/theme/css/forms.css").toExternalForm(),
                getClass().getResource("/theme/css/shape.css").toExternalForm(),
                getClass().getResource("/theme/css/typographic.css").toExternalForm(),
                getClass().getResource("/theme/css/helpers.css").toExternalForm(),
                getClass().getResource("/theme/css/master.css").toExternalForm()
        );

        decorator.fullBody();
        decorator.setContent(root);
        decorator.show();

//        decorator.testWithScenicView();
    }

    public Object getObject(String name) {
        try {
            InputStream input = new FileInputStream("app/app.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.get(name);

    }

    public String getString(String name) {
        try {
            InputStream input = new FileInputStream("app/app.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }

    public double getWidth() {
        return width.get();
    }

    public double getHeight() {
        return height.get();
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void store () {

        properties.stringPropertyNames().forEach(f -> properties.setProperty(f, properties.getProperty(f)));

        properties.setProperty("app.width", String.valueOf(decorator.getWidth()));
        properties.setProperty("app.height", String.valueOf(decorator.getHeight()));

        try {
            properties.store(new FileOutputStream("app/app.properties"), "Update on " + LocalDate.now());
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    public ResourceBundle getResourceBundle() {

        if(locale == null) {
            locale = new Locale(getString("app.lang"), getString("app.country"));
        }

        return ResourceBundle.getBundle("theme.i18n.Lang", locale);
    }
}
