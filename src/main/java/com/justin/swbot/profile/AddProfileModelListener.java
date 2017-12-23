/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.profile;

import java.util.Observer;

/**
 * Acts as observer for the {@link AddProfileModel}'s changes, every events occurs in
 * {@link AddProfileModel} will be notified to this. To register their interesting to observerable's
 * changes, by calling <code>addObserver</code>
 *
 * @author tuan3.nguyen@gmail.com
 */
public interface AddProfileModelListener extends Observer {

}
