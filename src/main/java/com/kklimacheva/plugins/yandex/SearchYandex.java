package com.kklimacheva.plugins.yandex;

import com.intellij.ide.BrowserUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;

import java.util.Optional;

public class SearchYandex extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Optional<PsiFile> psiFile = Optional.ofNullable(event.getData(LangDataKeys.PSI_FILE));
        String languageTag = psiFile
                .map(PsiFile::getLanguage)
                .map(Language::getDisplayName)
                .map(String::toLowerCase)
                .orElse("");

        Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            BrowserUtil.browse("https://yandex.ru/search/?text=" + selectedText + " " + languageTag);
        } else {
            Messages.showMessageDialog("Nothing to search. Please select what is needed.", "Oops", Messages.getErrorIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}