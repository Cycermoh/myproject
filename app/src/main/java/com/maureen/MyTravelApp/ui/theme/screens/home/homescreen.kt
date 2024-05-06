package com.maureen.MyTravelApp.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.maureen.MyTravelApp.navigation.ROUTE_HOME
import com.maureen.MyTravelApp.navigation.ROUTE_LOGIN
import com.maureen.MyTravelApp.navigation.ROUTE_REGISTER
import com.maureen.myapp.R

@Composable
fun HomeScreen(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ){
        Image(painter = painterResource(id = R.drawable.hotel1),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),

            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
                .padding(top = 10.dp)
        ){
            Box {
                Image(
                    painter = painterResource(id = R.drawable.bird),
                    contentDescription ="High 2",
                    modifier = Modifier
                        .padding(100.dp)
                        .size(100.dp)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Crop

                )

                Button(onClick = {
                    navController.navigate(ROUTE_REGISTER) {
                        popUpTo(ROUTE_HOME) { inclusive = true }
                    }
                }) {

                    Text(text = "register",color= Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                navController.navigate(ROUTE_LOGIN) {
                    popUpTo(ROUTE_HOME) { inclusive = true }
                }}) {
//
                Image(
                    painter = painterResource(id = R.drawable.aero1),
                    contentDescription = "null",
                    modifier = Modifier
                        .background(color = Color(0xff209fbe))
                        .size(20.dp)
                        .clip(shape = CircleShape)
                )
                Text(text = "browse",color= Color.Magenta)
            }
//
                Text(text = "Get started", color = Color.Yellow)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Homeprev() {
    HomeScreen(rememberNavController())
}








