package com.kklimacheva.plugins.yandex;

import com.intellij.ide.BrowserUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;

import java.util.Optional;

public class SearchYandexAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {

        Optional<PsiFile> psiFile = Optional.ofNullable(event.getData(LangDataKeys.PSI_FILE));
        String language = psiFile
                .map(PsiFile::getLanguage)
                .map(Language::getDisplayName)
                .map(String::toLowerCase)
                .orElse("");

        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        String text = selectedText + " " + language;
        String request = text.replace(' ', '+');

        if (selectedText != null) {
            BrowserUtil.browse("https://yandex.ru/search/?text=" + request);
        } else {
            Messages.showMessageDialog("Nothing to search. Please select what is needed.", "Oops", Messages.getErrorIcon());
        }
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}