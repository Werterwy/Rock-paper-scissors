package com.example.rock_paper_scissors;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView opponent1ImageView;
    private ImageView opponent2ImageView;
    private ImageView winImage1;
    private ImageView winImage2;
    private ImageView resultImageView;

    private CardView opponent1CardView;
    private CardView opponent2CardView;


    private boolean isOpponent1Spinning = false;
    private boolean isOpponent2Spinning = false;
    private boolean isplaying = false;
    private boolean isstop = false;
    private boolean isRotion = true;


    private int opponent1Result;
    private int opponent2Result;

    private final int ROCK = 0;
    private final int PAPER = 1;
    private final int SCISSORS = 2;

    private int currentImageIndex = 0;
    private int currentImageIndex2 = 0;

    private final int[] images = {R.drawable.rock, R.drawable.paper, R.drawable.scissors};
    private final int[] rotations = {R.drawable.rotation, R.drawable.rotation}; // Изображения вращения для двух соперников

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opponent1ImageView = findViewById(R.id.opponent1_image_view);
        opponent2ImageView = findViewById(R.id.opponent2_image_view);
        opponent1ImageView.setImageResource(images[currentImageIndex]);
        opponent2ImageView.setImageResource(images[currentImageIndex2]);
        resultImageView = findViewById(R.id.result_image_view);

        opponent1CardView = findViewById(R.id.opponent1_card_view);
        opponent2CardView = findViewById(R.id.opponent2_card_view);

    }

    // Метод для начала вращения фигур
    public void startSpinning(View view) {

        if(isplaying) {
           // fadeOutAndChangeImage(opponent1ImageView, images[currentImageIndex]);

            opponent1CardView.animate().alpha(1f).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    // Установка нового изображения и возвращение прозрачности к 1
                    //imageView.setImageResource(newImageResource);
                    //imageView.animate().alpha(1f).setDuration(2000);
                }
            });
            // fadeOutAndChangeImage(opponent2ImageView, images[currentImageIndex2]);
            opponent2CardView.animate().alpha(1f).setDuration(1000).withEndAction(new Runnable() {
                @Override
                public void run() {
                    // Установка нового изображения и возвращение прозрачности к 1
                    //imageView.setImageResource(newImageResource);
                    //imageView.animate().alpha(1f).setDuration(2000);
                }
            });
        }else {
            opponent2ImageView.setImageResource(images[currentImageIndex2]);
            opponent1ImageView.setImageResource(images[currentImageIndex2]);
        }
        isplaying = true;


        if (!isOpponent1Spinning) {
            isOpponent1Spinning = true;
            //opponent1ImageView.setImageResource(rotations[0]); // Установка изображения вращения для соперника 1
            // Добавьте код для вращения соперника 1
            rotateOpponent1();
            // Запуск таймера для остановки вращения через случайное время (например, от 1 до 5 секунд)
           // new Handler().postDelayed(this::stopSpinningOpponent1, new Random().nextInt(5000 - 1000) + 1000);
        }

        if (!isOpponent2Spinning) {
            isOpponent2Spinning = true;
            //opponent2ImageView.setImageResource(rotations[1]); // Установка изображения вращения для соперника 2
            // Добавьте код для вращения соперника 2
            rotateOpponent2();
            // Запуск таймера для остановки вращения через случайное время (например, от 1 до 5 секунд)
           // new Handler().postDelayed(this::stopSpinningOpponent2, new Random().nextInt(5000 - 1000) + 1000);
        }
        isstop = true;

    }

    // Метод для вращения соперника 1
    private void rotateOpponent1() {
            opponent1ImageView.setImageResource(images[currentImageIndex]);
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(100); // Продолжительность анимации (в миллисекундах)
            rotateAnimation.setInterpolator(new LinearInterpolator()); // Линейная интерполяция
            rotateAnimation.setRepeatCount(Animation.INFINITE); // Бесконечное повторение анимации
            opponent1ImageView.startAnimation(rotateAnimation); // Запуск анимации вращения для соперника 1
            currentImageIndex = (currentImageIndex + 1) % images.length;
    }

    // Метод для вращения соперника 1
    private void rotateOpponent2() {
            opponent2ImageView.setImageResource(images[currentImageIndex2]);
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(100); // Продолжительность анимации (в миллисекундах)
            rotateAnimation.setInterpolator(new LinearInterpolator()); // Линейная интерполяция
            rotateAnimation.setRepeatCount(Animation.INFINITE); // Бесконечное повторение анимации
            opponent2ImageView.startAnimation(rotateAnimation); // Запуск анимации вращения для соперника 1
            currentImageIndex2 = (currentImageIndex + 1) % images.length;
    }

    public void stopSpinning(View view){
        if(isstop) {
            stopSpinningOpponent1();
            stopSpinningOpponent2();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    determineWinner();
                }
            }, 800); // Задержка в миллисекундах (1 секунда = 1000 миллисекунд)
        }else {
            
        }
        isstop = false;
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Вызов метода determineWinner() после задержки в 1 секунду
                determineWinner();
                // Остановка таймера после выполнения задачи
                timer.cancel();
            }
        }, 2000); // Задержка в миллисекундах (1 секунда = 1000 миллисекунд)*/

    }
    // Метод для остановки вращения фигур
    private void stopSpinningOpponent1() {
        isOpponent1Spinning = false;
        opponent1ImageView.clearAnimation(); // Остановка анимации вращения для соперника 1
        // Добавьте код для остановки вращения соперника 1
        opponent1Result = new Random().nextInt(3); // Случайный выбор результата соперника 1
        opponent1ImageView.setImageResource(images[opponent1Result]); // Отображение выбранной фигуры соперника 1
        currentImageIndex = 0;
    }

    // Метод для остановки вращения фигур
    private void stopSpinningOpponent2() {
        isOpponent2Spinning = false;
        opponent2ImageView.clearAnimation(); // Остановка анимации вращения для соперника 1
        // Добавьте код для остановки вращения соперника 2
        opponent2Result = new Random().nextInt(3); // Случайный выбор результата соперника 2
        opponent2ImageView.setImageResource(images[opponent2Result]); // Отображение выбранной фигуры соперника 2
        currentImageIndex2 = 0;
    }

    // Метод для определения победителя
    private void determineWinner() {
        if (!isOpponent1Spinning && !isOpponent2Spinning) {
            if (opponent1Result == ROCK && opponent2Result == SCISSORS ||
                    opponent1Result == SCISSORS && opponent2Result == PAPER ||
                    opponent1Result == PAPER && opponent2Result == ROCK) {
                // Установка изображения победителя для соперника 1
               // opponent1ImageView.setImageResource(R.drawable.win);

                // Анимация изменения прозрачности для соперника 1
                fadeOutAndChangeImage(opponent1CardView, R.drawable.win);
            } else if (opponent1Result == opponent2Result) {
                // Ничья
                // Добавьте дополнительные действия для ничьей
            } else {
                // Установка изображения победителя для соперника 2
               // opponent2ImageView.setImageResource(R.drawable.win);
                // Анимация изменения прозрачности для соперника 2
                fadeOutAndChangeImage(opponent2CardView, R.drawable.win);
            }
        }
    }

    private void fadeOutAndChangeImage(final CardView cardView, final int newImageResource) {
        // Анимация изменения прозрачности
        cardView.animate().alpha(0f).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                // Установка нового изображения и возвращение прозрачности к 1
               // cardView.setAlpha(1f); // Возвращаем прозрачность к 1
                //ImageView imageView = cardView.findViewById(R.id.opponent1_image_view);
                //imageView.setImageResource(newImageResource); // Устанавливаем новое изображение
            }
        });
    }
       /* private void fadeOutAndChangeImage(final ImageView imageView, final int newImageResource) {
            // Анимация изменения прозрачности
            imageView.animate().alpha(0f).setDuration(600).withEndAction(new Runnable() {
                @Override
                public void run() {
                    // Установка нового изображения и возвращение прозрачности к 1
                    //imageView.setImageResource(newImageResource);
                    //imageView.animate().alpha(1f).setDuration(2000);
                }
            });
        }*/


}
