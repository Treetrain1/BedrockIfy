package me.juancarloscp52.bedrockify.mixin.client.features.loadingScreens;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import me.juancarloscp52.bedrockify.client.BedrockifyClient;
import me.juancarloscp52.bedrockify.client.features.loadingScreens.LoadingScreenWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MessageScreen.class)
public class SaveLevelScreenMixin {

    /**
     * Renders the loading screen widget.
     */
    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawCenteredTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;III)V"))
    public void drawLoadingScreenWidget(DrawContext instance, TextRenderer textRenderer, Text text, int centerX, int y, int color, Operation<Void> original) {
        if(BedrockifyClient.getInstance().settings.isLoadingScreenEnabled()){
            LoadingScreenWidget.getInstance().render(instance, MinecraftClient.getInstance().getWindow().getScaledWidth() / 2, MinecraftClient.getInstance().getWindow().getScaledHeight() / 2, Text.literal(text.getString()), null, -1);
        }else{
            original.call(instance, textRenderer, text, centerX, y, color);
        }
    }
}
