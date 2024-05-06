package com.maureen.MyTravelApp.ui.theme.screens.Destination

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import java.time.LocalDate

data class Destination(
    val id: String,
    val name: String,
    val description: String,
    val imageResId: Int, // Resource ID for an image
    val reviews: List<Review> // List of user reviews
)

data class Review(
    val reviewerName: String,
    val reviewDate: LocalDate,
    val rating: Float, // Rating out of 5
    val reviewText: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationDetailScreen(
    destination: Destination,
    onBookNow: () -> Unit,
    onAddToFavorites: (Boolean) -> Unit,
    isFavorite: Boolean
) {
    var isFav by remember { mutableStateOf(isFavorite) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(destination.name) },
                actions = {
                    IconButton(onClick = {
                        isFav = !isFav
                        onAddToFavorites(isFav)
                    }) {
                        Icon(
                            imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFav) "Remove from favorites" else "Add to favorites"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                item {
                    // Display the destination image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        Image(
                            painter = painterResource(destination.imageResId),
                            contentDescription = "Destination Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Description of the destination
                    Text(
                        text = destination.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Button for booking
                    Button(
                        onClick = onBookNow,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Book Now")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Heading for reviews
                    Text(
                        text = "Reviews",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

                // List of reviews
                items(destination.reviews) { review ->
                    ReviewItem(review = review)
                }
            }
        }
    )
}

@Composable
fun ReviewItem(
    review: Review
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = review.reviewerName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "Rating: ${review.rating}",
                    fontSize = 16. sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "Reviewed on ${review.reviewDate}",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = review.reviewText,
                fontSize = 14 .sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Homeprev() {
    DestinationDetailScreen(rememberNavController())
}