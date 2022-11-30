package com.kklimacheva.plugins.yandex;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class TranslateYandexAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        String request = selectedText.replace(' ', '+');

        if (selectedText != null) {
            BrowserUtil.browse("https://translate.yandex.ru/?source_lang=en&target_lang=ru&text=" + request);
        } else {
            Messages.showMessageDialog("Nothing to translate. Please select what is needed.", "Oops", Messages.getErrorIcon());
        }
    }


    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
