package kz.mirinda.trainer.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * new class
 *
 * @author mirinda
 */
public class DrawView extends View {
	Paint paint= new Paint();
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);    //To change body of overridden methods use File | Settings | File Templates.
		paint.setColor(Color.GREEN);
		canvas.drawCircle(10,10,15,paint);
	}

	public DrawView(Context context) {
		super(context);

	}

}
