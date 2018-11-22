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

package com.gn;

import com.gn.control.UserDetail;
import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import com.gn.module.loader.Loader;
import com.gn.module.main.Main;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.scenicview.ScenicView;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  07/10/2018
 * Version 1.0
 */
public class App extends Application {

    private float  increment = 0;
    private byte   progress = 0;

    @Override
    public synchronized void init(){
        byte total = 42; // the difference represents the views not loaded
        increment = 100 / total;
        load("designer", "carousel");
        load("designer", "cards");
        load("designer", "banners");

        load("controls", "button");
        load("controls", "toggle");
        load("controls", "textfield");
        load("controls", "datepicker");
        load("controls", "checkbox");
        load("controls", "radiobutton");
        load("controls", "combobox");
        load("controls", "choicebox");
        load("controls", "splitmenubutton");
        load("controls", "menubutton");
        load("controls", "menubar");
        load("controls", "colorpicker");
        load("controls", "slider");
        load("controls", "spinner");
        load("controls", "progressbar");
        load("controls", "progressindicator");
        load("controls", "pagination");
        load("controls", "mediaview");
        load("controls", "listview");
        load("controls", "label");
        load("controls", "hyperlink");
        load("controls", "imageview");
        load("controls", "tableview");
        load("controls", "scrollbar");
        load("controls", "passwordfield");
        load("controls", "treeview");
        load("controls", "treetableview");
        load("dashboard", "dashboard");

        load("charts", "piechart");
        load("charts", "areachart");
        load("charts", "barchart");
        load("charts", "bubblechart");
        load("charts", "linechart");
        load("charts", "stackedbarchart");
        load("charts", "stackedareachart");
        load("charts", "scatterchart");

        load("main",     "main");

        load("profile", "profile");

        load("login", "login");

//        System.out.println(ViewManager.getInstance().getSize());

        // delay
        try {
            wait(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){

    }

    public static ObservableList<String>    stylesheets;
    public static Scene                     scene;
    public static GNDecorator               decorator;

    @Override
    public  void start(Stage primary) {
        GNDecorator decorator = new GNDecorator();
        App.decorator = decorator;
        scene = decorator.getScene();
        decorator.setTitle("Dashboard Fx");
        decorator.setContent(ViewManager.getInstance().get("login"));
        decorator.initTheme(GNDecorator.Theme.DEFAULT);
        decorator.addButton(ButtonType.FULL_EFFECT);

        stylesheets = decorator.getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource("/com/gn/theme/css/fonts.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/material-color.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/skeleton.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/light.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/forms.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/typographic.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/helpers.css").toExternalForm(),
                getClass().getResource("/com/gn/theme/css/master.css").toExternalForm()
        );

        UserDetail detail = new UserDetail();
//        decorator.addCustom(detail);
//        detail.setProfileAction(event -> {
//            Main.ctrl.title.setText("Profile");
//            Main.ctrl.body.setContent(ViewManager.getInstance().get("login"));
//            detail.getPopOver().hide();
//        });

        decorator.setMaximized(true);
        decorator.getStage().setOnCloseRequest(event -> {
            detail.getPopOver().hide();

            if(Main.popConfig.isShowing()) Main.popConfig.hide();
            if(Main.popup.isShowing()) Main.popup.hide();

            Platform.exit();
        });


        decorator.getStage().setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
//                try {
//                    Parent root = FXMLLoader.load(getClass().getResource("/com/gn/module/login/login.fxml"));
//                    JFXDialogLayout layout = new JFXDialogLayout();
//                    layout.setBody(root);
//                    JFXDialog welcome = new JFXDialog();
//                    welcome.setContent(layout);
//                    welcome.setDialogContainer(decorator.getBackground());
//                    welcome.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

        decorator.getStage().getIcons().add(new Image("/com/gn/module/media/icon.png"));
        decorator.show();


//        Properties props = new Properties();
//
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");

//        Session session = Session.getDefaultInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication()
//                    {
//                        return new PasswordAuthentication("gleidisonmt@gmail.com", "Developerglen");
//                    }
//                });
//
////        Session session = Session.getDefaultInstance(props);
//
//        SimpleEmail email = new SimpleEmail();
////        email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
//        try {
//            email.setMailSession(session);
//            email.addTo("jezieljunio10@gmail.com", "Jeziel Junio"); //destinatário
//            email.setFrom("gleidisonmt@gmail.com", "Me"); // remetente
//            email.setSubject("Mensagem de Teste"); // assunto do e-mail
//            email.setMsg("Teste de Email utilizando commons-email"); //conteudo do e-mail
//            email.send(); //envia o e-mail
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
//
//        /** Ativa Debug para sessão */
//        session.setDebug(true);

//        ScenicView.show(decorator.getScene());
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, Loader.class, args);
    }

    private void load(String module, String name){
        try {
                ViewManager.getInstance().put(
                        name,
                        FXMLLoader.load(getClass().getResource("/com/gn/module/" + module + "/" + name + ".fxml"))
                );
                preloaderNotify();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
    }
}
