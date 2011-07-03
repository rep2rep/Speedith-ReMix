/*
 *   Project: Speedith.Core
 * 
 * File name: InferenceRuleProvider.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2011 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package speedith.core.reasoning;

import java.util.Locale;
import speedith.core.reasoning.args.RuleArg;

/**
 * Contains detailed information about the inference rules it provides (through
 * the {@link InferenceRules factory of inference rules}).
 * <p>Instances of classes that implement interface provide the following
 * information:
 *  <ul>
 *      <li>an explanation of what the {@link InferenceRule inference rule} does
 *          (for the user),</li>
 *      <li>a description of parameters it takes (for the user as well as for
 *          the SRK),</li>
 *      <li>TODO (others?).</li>
 *  </ul>
 * </p>
 * @param <TArgs> the type of arguments the provided inference rule expects. Use
 * the type {@link RuleArg} to specify that the inference rule does not expect
 * any specific arguments.
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public interface InferenceRuleProvider<TArgs extends RuleArg> {
    /**
     * Returns an instance of the {@link InferenceRule inference rule} this
     * class provides.
     * <p>Note that the main purpose of this class is to give more
     * information on the inference rule, without having to create an actual
     * instance of the inference rule itself. For example, this class provides
     * information on what arguments the inference rule accepts, a description
     * on how it can be used, and what its effects are.</p>
     * <p><span style="font-weight:bold">Note</span>: this method may return the
     * same instance of the inference rule for many invocations.</p>
     * @return 
     */
    public abstract InferenceRule<TArgs> getInferenceRule();

    /**
     * Returns the name of the {@link InferenceRule} this provider provides.
     * <p><span style="font-weight:bold">Note</span>: this name is not
     * internationalised.</p>
     * @return the name of the {@link InferenceRule} this provider provides.
     */
    public abstract String getInferenceRuleName();

    /**
     * Returns the description of the provided inference rule.
     * <p>By default, this method should call the {@link
     * InferenceRuleProvider#getDescription(java.util.Locale)} method with the
     * {@link Locale#getDefault() default locale}.</p>
     * @return the description of the provided inference rule.
     */
    public String getDescription();

    /**
     * Returns the description of the provided inference rule in the given
     * {@link Locale locale}.
     * @param locale the locale in which to give the description.
     * @return the description of the provided inference rule in the given
     * {@link Locale locale}.
     */
    public abstract String getDescription(Locale locale);
    
    /**
     * Returns the type of the argument the provided inference rule requires.
     * The argument of this type is required in the inference rules' {@link
     * InferenceRule#apply(speedith.core.reasoning.args.RuleArg, speedith.core.reasoning.Goals)
     * apply method}.
     * <p>Also, see the {@link InferenceRuleProvider#getDescription()
     * description} for more information on how to use the inference rule.</p>
     * @return the type of the argument the provided inference rule requires.
     */
    public abstract Class<TArgs> getArgumentType();
}
