from turtle import Screen
from paddle import Paddle
from game_ball import Ball
from score_board import ScoreBoard
import time

screen = Screen()
screen.tracer(0)
screen.bgcolor("black")
screen.setup(width=800, height=600)
screen.title("Pong Game")

canvas = screen.getcanvas()
root = canvas.winfo_toplevel()

player_1 = Paddle((350, 0))
player_2 = Paddle((-350, 0))
ball = Ball()
scoreboard = ScoreBoard()

screen.listen()
screen.onkeypress(key="Up", fun=player_1.move_up)
screen.onkeypress(key="Down", fun=player_1.move_down)
screen.onkeypress(key="w", fun=player_2.move_up)
screen.onkeypress(key="s", fun=player_2.move_down)


def on_close():
    global game_on
    game_on = False


root.protocol("WM_DELETE_WINDOW", on_close)
game_on = True

while game_on:

    time.sleep(ball.move_speed)
    screen.update()
    ball.move_ball()
    # exiting the game
    if not game_on:
        break

    # ball boundary check
    if ball.ycor() > 270 or ball.ycor() < -270:
        ball.bounce_y()

    # ball collision check with paddle
    if ball.distance(player_1) < 50 and ball.xcor() > 320 or ball.distance(player_2) < 50 and ball.xcor() < -320:
        ball.bounce_x()

    # score board
    if ball.xcor() > 380:
        ball.reset_position()
        scoreboard.r_point()
    if ball.xcor() < -380:
        ball.reset_position()
        scoreboard.l_point()
