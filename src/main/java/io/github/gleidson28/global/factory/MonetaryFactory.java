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
package io.github.gleidson28.global.factory;

import io.github.gleidson28.global.model.Professional;
import io.github.gleidson28.global.util.MoneyUtil;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.math.BigDecimal;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  26/09/2021
 */
public class MonetaryFactory<E extends Professional> implements Callback<TableColumn<E, BigDecimal>, TableCell<E, BigDecimal>> {

    @Override
    public TableCell<E, BigDecimal> call(TableColumn<E, BigDecimal> param) {
        return new TableCell<E, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal item, boolean empty) {
                super.updateItem(item, empty);
                if(item != null) {
                    setText(MoneyUtil.format(item));
                    setItem(item);
                    setGraphic(null);
                } else {
                    setText(null);
                    setItem(null);
                    setGraphic(null);
                }
            }
        };
    }

}