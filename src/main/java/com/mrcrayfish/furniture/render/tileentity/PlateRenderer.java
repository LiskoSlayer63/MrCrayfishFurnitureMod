/**
 * MrCrayfish's Furniture Mod
 * Copyright (C) 2016  MrCrayfish (http://www.mrcrayfish.com/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mrcrayfish.furniture.render.tileentity;

import com.mrcrayfish.furniture.tileentity.TileEntityPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import org.lwjgl.opengl.GL11;

public class PlateRenderer extends TileEntitySpecialRenderer<TileEntityPlate>
{
	private EntityItem entityFood = new EntityItem(Minecraft.getMinecraft().world, 0D, 0D, 0D);

	@Override
	public void render(TileEntityPlate plate, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		if (plate.getFood() != null)
		{
			entityFood.setItem(plate.getFood());

			GL11.glPushMatrix();
			this.entityFood.hoverStart = 0.0F;

			float xOffset = 0.0F;
			float zOffset = 0.0F;
			
			switch (plate.getRotation())
			{
			case 0:
				zOffset -= 0.15F;
				break;
			case 1:
				xOffset += 0.35F;
				zOffset += 0.2F;
				break;
			case 2:
				zOffset += 0.55F;
				break;
			case 3:
				xOffset -= 0.35F;
				zOffset += 0.2F;
				break;
			}
			
			GL11.glDisable(GL11.GL_LIGHTING);
			//WorldRenderer renderer = Tessellator.getInstance().getWorldRenderer();
			//renderer.setBrightness(15728880);

			GL11.glTranslatef((float) x + 0.5F + xOffset, (float) y + 0.05F, (float) z + 0.3F + zOffset);
			GL11.glRotatef(plate.getRotation() * -90F, 0, 1, 0);
			GL11.glRotatef(180, 0, 1, 1);
			GlStateManager.translate(0, -0.1, 0);
			Minecraft.getMinecraft().getRenderManager().renderEntity(entityFood, 0.0D, 0.0D, 0.075D, 0.0F, 0.0F, false);
			
			GL11.glEnable(GL11.GL_LIGHTING);
			
			GL11.glPopMatrix();
		}
	}
}
