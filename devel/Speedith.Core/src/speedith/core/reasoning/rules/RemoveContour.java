/*
 *   Project: Speedith.Core
 * 
 * File name: RemoveContour.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2012 Matej Urbas
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
package speedith.core.reasoning.rules;

import java.util.Locale;
import speedith.core.i18n.Translations;
import speedith.core.reasoning.Goals;
import speedith.core.reasoning.InferenceRule;
import speedith.core.reasoning.RuleApplicationException;
import speedith.core.reasoning.RuleApplicationInstruction;
import speedith.core.reasoning.RuleApplicationResult;
import speedith.core.reasoning.args.ContourArg;
import speedith.core.reasoning.args.RuleArg;

/**
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class RemoveContour extends SimpleInferenceRule<ContourArg> {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The name of this inference rule.
     */
    public static final String InferenceRuleName = "remove_contour";
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inference Rule Implementation">
    @Override
    public RuleApplicationResult apply(RuleArg args, Goals goals) throws RuleApplicationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public InferenceRule<ContourArg> getInferenceRule() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String getInferenceRuleName() {
        return InferenceRuleName;
    }
    
    @Override
    public String getDescription(Locale locale) {
        return Translations.i18n(locale, "REMOVE_CONTOUR_DESCRIPTION");
    }

    @Override
    public String getCategory(Locale locale) {
        return Translations.i18n(locale, "INF_RULE_CATEGORY_PURELY_DIAGRAMMATIC");
    }
    
    @Override
    public String getPrettyName(Locale locale) {
        return Translations.i18n(locale, "REMOVE_CONTOUR_PRETTY_NAME");
    }
    
    @Override
    public Class<ContourArg> getArgumentType() {
        return ContourArg.class;
    }
    
    @Override
    public RuleApplicationInstruction<ContourArg> getInstructions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>
    
}
