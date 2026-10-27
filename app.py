import random

import streamlit as st

st.set_page_config(page_title="Random Number Generator", page_icon="🎲")
st.title("🎲 Random Number Generator")
st.write("Choose a range, then generate a random integer.")

col1, col2 = st.columns(2)
with col1:
	minimum = st.number_input("Minimum", value=1, step=1)
with col2:
	maximum = st.number_input("Maximum", value=100, step=1)

if st.button("Generate", type="primary"):
	if minimum > maximum:
		st.error("Minimum must be less than or equal to maximum.")
	else:
		result = random.randint(int(minimum), int(maximum))
		st.metric("Your random number", result)