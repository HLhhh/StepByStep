package com.csyl.factory;

/**
 * @author 霖
 */
public interface DoFactory<source, target> {

    target bulider(source source);

    source reverse(target target);
}
