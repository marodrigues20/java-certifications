package chapter_8.serializable.domain;

import java.io.Serializable;

/**
 * Cat contains an instance of Tail, and both of those classes are marked Serializable, so no problems there.
 * Unfortunately, Tail contains an instance of Fur that is not marked Serializable.
 *
 * Either of the following changes fixes the problem and allows Cat to be serialized:
 * 1) Mark fur reference as transient
 * 2) Mark Fur class as Serializable
 */
public class Cat implements Serializable {

    private Tail tail = new Tail();

}
