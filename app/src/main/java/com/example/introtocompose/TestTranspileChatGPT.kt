package com.example.introtocompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ProductCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .background(Color(0xFFFFF1F1)) // fundo rosa claro
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFE3E8EE),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "New release",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF4A4A4A)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Cute Cat toy",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Price : 300$",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "4.0",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (index < 4) Color(0xFFFFC107) else Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* ação */ },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation(2.dp)
                ) {
                    Text(text = "Read More", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFFFFCFCF), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_electric_car_24), // substitua pela sua imagem
                    contentDescription = "Cute cat toy",
                    modifier = Modifier.size(80.dp)
                )
            }
        }
    }
}