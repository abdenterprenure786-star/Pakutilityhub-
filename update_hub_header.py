with open('app/src/main/java/com/example/ui/components/HubHeader.kt', 'r', encoding='utf-8') as f:
    text = f.read()

old_center_brand = """            // Center Brand Title (Tap to toggle full overview)
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .clickable { isExpanded = !isExpanded }
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PakUtility ",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
                Text(
                    text = "PRO",
                    style = MaterialTheme.typography.titleMedium,
                    color = HubGold,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Black,
                    fontSize = 15.sp
                )
            }"""

new_center_brand = """            // Center Brand Title (Tap to toggle full overview) with All-Rates Logo
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { isExpanded = !isExpanded }
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .border(1.2.dp, HubBorderGold, RoundedCornerShape(7.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.pak_rates_logo),
                        contentDescription = "PakUtility Rates Logo",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "PakUtility",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.White,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(HubGold)
                                .padding(horizontal = 4.dp, vertical = 1.dp)
                        ) {
                            Text(
                                text = "HUB",
                                color = Color.Black,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                    Text(
                        text = "تمام چیزوں کے لائیو ریٹس",
                        style = MaterialTheme.typography.labelSmall,
                        color = HubGold,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }"""

if old_center_brand in text:
    text = text.replace(old_center_brand, new_center_brand)
    with open('app/src/main/java/com/example/ui/components/HubHeader.kt', 'w', encoding='utf-8') as f:
        f.write(text)
    print("HubHeader.kt updated with all-rates logo.")
else:
    print("old_center_brand not matched in HubHeader.kt")
