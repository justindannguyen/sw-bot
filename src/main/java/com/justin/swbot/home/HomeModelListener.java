/**
 * Copyright (C) 2017, Justin Nguyen
 */
package com.justin.swbot.home;

import java.util.Observer;

/**
 * Acts as observer for the {@link HomeModel}'s changes, every events occurs in {@link HomeModel} will be notified to
 * this. To register their interesting to observerable's changes, by calling <code>addObserver</code>
 *
 * @author tuan3.nguyen@gmail.com
 */
public interface HomeModelListener extends Observer {

}
