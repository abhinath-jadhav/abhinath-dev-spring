import React, { useEffect, useState } from "react";

import FoodItemCard from "./FoodHomeCard";
import Container from "./Container";
import { FoodApi } from "../utils";
import Loading from "./Loading";

const HomeBody = () => {
  const [foodItems, setFoodItems] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    const fetchData = async () => {
      const data = await FoodApi.fetchHomeData("/food/favorites");
      if (data.status == 200) {
        const { items } = data;
        setFoodItems(items);
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading)
    return (
      <>
        <Loading />
      </>
    );
  return (
    <div className="mt-2 font-semibold">
      <Container>
        <div>
          <h3 className="text-2xl text-center">Café Akasa Favourites</h3>

          <div className="md:flex md:flex-wrap justify-center mt-4">
            {foodItems.map((o) => (
              <FoodItemCard key={o.id} {...o} />
            ))}
          </div>
        </div>
      </Container>
    </div>
  );
};

export default HomeBody;
