package me.jessyan.retrofiturlmanager.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Adds headers literally supplied in the {@code value}.
 * <pre><code>
 * &#64;Headers("Cache-Control: max-age=640000")
 * &#64;GET("/")
 * ...
 *
 * &#64;Headers({
 *   "X-Foo: Bar",
 *   "X-Ping: Pong"
 * })
 * &#64;GET("/")
 * ...
 * </code></pre>
 * <strong>Note:</strong> Headers do not overwrite each other. All headers with the same name will
 * be included in the request.
 * <p>
 * Created by 蓝兵 on 2018/4/18.
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RUNTIME)
public @interface AllHeaders {

    String[] value();
}
