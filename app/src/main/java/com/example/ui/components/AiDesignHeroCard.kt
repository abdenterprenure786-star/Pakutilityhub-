package com.example.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.HubCosmicViolet
import com.example.ui.theme.HubElectricIndigo
import com.example.ui.theme.HubGold
import com.example.ui.theme.HubNeonCyan
import com.example.ui.theme.HubNeonPurpleGlow
import com.example.ui.theme.HubRoyalBlue
import com.example.ui.theme.HubTextDim
import com.example.ui.theme.HubTextPrimary
import com.example.ui.theme.HubTextSecondary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AiDesignHeroCard(
    modifier: Modifier = Modifier,
    onChipClick: ((String) -> Unit)? = null
) {
    val context = LocalContext.current
    val infiniteTransition = rememberInfiniteTransition(label = "portal_glow")

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(26.dp),
                spotColor = HubCosmicViolet.copy(alpha = 0.5f),
                ambientColor = HubRoyalBlue.copy(alpha = 0.3f)
            )
            .border(
                width = 1.5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        HubNeonPurpleGlow.copy(alpha = 0.8f),
                        HubRoyalBlue.copy(alpha = 0.6f),
                        HubNeonCyan.copy(alpha = 0.8f)
                    )
                ),
                shape = RoundedCornerShape(26.dp)
            )
            .testTag("ai_design_hero_card"),
        shape = RoundedCornerShape(26.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0A0F24)
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF141A38),
                            Color(0xFF0A0F24),
                            Color(0xFF060914)
                        )
                    )
                )
                .padding(22.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Circular Avatar Portal with Multi-layered Glowing Aura
                Box(
                    modifier = Modifier
                        .size(170.dp)
                        .testTag("circular_avatar_portal"),
                    contentAlignment = Alignment.Center
                ) {
                    // Outer Cosmic Spinning Aura Ring
                    Box(
                        modifier = Modifier
                            .size(166.dp)
                            .rotate(rotationAngle)
                            .clip(CircleShape)
                            .background(
                                Brush.sweepGradient(
                                    colors = listOf(
                                        HubCosmicViolet,
                                        HubNeonCyan,
                                        HubRoyalBlue,
                                        HubNeonPurpleGlow,
                                        HubGold,
                                        HubCosmicViolet
                                    )
                                )
                            )
                    )

                    // Soft Frosted Blur Cushion
                    Box(
                        modifier = Modifier
                            .size(154.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF090C1A))
                    )

                    // Inner Pulsing Corona Ring
                    Box(
                        modifier = Modifier
                            .size((146 * pulseScale).dp)
                            .clip(CircleShape)
                            .border(
                                width = 2.5.dp,
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        HubNeonPurpleGlow,
                                        HubNeonCyan,
                                        Color.Transparent
                                    )
                                ),
                                shape = CircleShape
                            )
                    )

                    // Circular Portrait Art
                    Image(
                        painter = painterResource(id = R.drawable.img_ai_creator_portal),
                        contentDescription = "AI Design Systems Creator Portal",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(140.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White.copy(alpha = 0.8f), CircleShape)
                    )

                    // Floating Ethereal Spheres (Orbital Particles from Reference Photo)
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .offset(x = (-76).dp, y = (-20).dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(Color.White, HubNeonCyan, HubCosmicViolet)
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .offset(x = 76.dp, y = (-26).dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(Color.White, HubNeonPurpleGlow, HubRoyalBlue)
                                )
                            )
                    )

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .offset(x = (-64).dp, y = 50.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(Color.White, HubRoyalBlue)
                                )
                            )
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Headline Line 1: Elegant Editorial Serif
                Text(
                    text = "Follow for More",
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    fontSize = 28.sp,
                    letterSpacing = (-0.5).sp,
                    textAlign = TextAlign.Center
                )

                // Headline Line 2: Vibrant Electric Gradient Title
                Text(
                    text = "AI Design Systems",
                    style = MaterialTheme.typography.displayLarge.copy(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF60A5FA), // Light Blue
                                HubCosmicViolet,    // Violet
                                Color(0xFFE879F9)   // Bright Fuchsia
                            )
                        )
                    ),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp,
                    letterSpacing = (-0.5).sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Elegant Subtitle with Sparkle Divider
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .width(20.dp)
                            .background(HubCosmicViolet.copy(alpha = 0.5f))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = HubGold,
                        modifier = Modifier.size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .width(20.dp)
                            .background(HubCosmicViolet.copy(alpha = 0.5f))
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Learn how to write better prompts,\nbuild better websites, and create better AI workflows.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFC7D2FE),
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Frosted Glass Sparkle Pill Chips (2x2 Grid)
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    listOf(
                        "Website Design" to "✦ Website Design",
                        "AI Prompts" to "✦ AI Prompts",
                        "AI Workflows" to "✦ AI Workflows",
                        "Creative Direction" to "✦ Creative Direction"
                    ).forEach { (key, label) ->
                        FrostedSparkleChip(
                            label = label,
                            onClick = {
                                onChipClick?.invoke(key)
                                Toast.makeText(context, "$label Activated", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Frosted Glass Profile Handle Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0x331E1B4B))
                        .border(
                            width = 1.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    HubCosmicViolet.copy(alpha = 0.6f),
                                    HubRoyalBlue.copy(alpha = 0.4f),
                                    HubNeonCyan.copy(alpha = 0.6f)
                                )
                            ),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .clickable {
                            Toast.makeText(context, "@future_.designer.ai • Pak Utility Hub Official", Toast.LENGTH_SHORT).show()
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .testTag("designer_handle_pill"),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(HubCosmicViolet.copy(alpha = 0.3f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color(0xFFA78BFA),
                                modifier = Modifier.size(15.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "@future_.designer.ai",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xFFE0E7FF),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Editorial Quote Container (from reference text in photo)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0x26000000))
                        .border(1.dp, Color(0x33A5B4FC), RoundedCornerShape(14.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Text(
                            text = "“Most people blame AI when their website looks generic. Build with taste, precision, and architectural elegance.”",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFE2E8F0),
                            fontFamily = FontFamily.Serif,
                            fontSize = 12.5.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                ActionPillButton(
                                    icon = Icons.Default.Share,
                                    label = "Share",
                                    onClick = {
                                        Toast.makeText(context, "AI Design link shared", Toast.LENGTH_SHORT).show()
                                    }
                                )
                                ActionPillButton(
                                    icon = Icons.Default.BookmarkBorder,
                                    label = "Save",
                                    onClick = {
                                        Toast.makeText(context, "Design system bookmarked", Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFF818CF8))
                                    .clickable {
                                        Toast.makeText(context, "Exploring AI Design Systems", Toast.LENGTH_SHORT).show()
                                    }
                                    .padding(horizontal = 14.dp, vertical = 6.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Visit",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Icon(
                                        imageVector = Icons.Default.OpenInNew,
                                        contentDescription = null,
                                        tint = Color.Black,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FrostedSparkleChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0x281E1B4B))
            .border(
                width = 1.dp,
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0x66C7D2FE),
                        Color(0x44818CF8),
                        Color(0x66A78BFA)
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 7.dp)
            .testTag("frosted_chip_${label.replace(" ", "_").lowercase()}"),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFFF1F5F9),
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp
        )
    }
}

@Composable
private fun ActionPillButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x33334155))
            .border(1.dp, Color(0x4464748B), RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFFCBD5E1),
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFCBD5E1),
                fontSize = 10.sp
            )
        }
    }
}
