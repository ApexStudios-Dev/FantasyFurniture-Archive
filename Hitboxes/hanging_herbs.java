Stream.of(
Block.box(2, 14, 12.5, 3, 16, 13.5),
Block.box(13, 14, 12.5, 14, 16, 13.5),
Block.box(1, 12, 12, 15, 14, 14),
Block.box(0.5, 1, 10.5, 15.5, 12, 15.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();